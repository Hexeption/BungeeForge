package dev.hexeption.bungeeforge.mixin.mixins.bungee;

import com.google.gson.Gson;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;
import dev.hexeption.bungeeforge.config.BFConfig;
import dev.hexeption.bungeeforge.mixin.interfaces.ICHandshakePacket;
import dev.hexeption.bungeeforge.mixin.interfaces.INetworkManager;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ProtocolType;
import net.minecraft.network.handshake.ServerHandshakeNetHandler;
import net.minecraft.network.handshake.client.CHandshakePacket;
import net.minecraft.network.login.server.SDisconnectLoginPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * MixinServerHandshakeNetHandler
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 10:25 pm
 */
@Mixin(ServerHandshakeNetHandler.class)
public class MixinServerHandshakeNetHandler {

    @Shadow
    @Final
    private NetworkManager networkManager;
    private static final Gson gson = new Gson();


    @Inject(method = "processHandshake", at = @At(value = "HEAD"), cancellable = true)
    private void onProcessHandshake(CHandshakePacket packetIn, CallbackInfo ci) {
        if (BFConfig.bungeeCord && packetIn.getRequestedState().equals(ProtocolType.LOGIN)) {
            System.out.println(((ICHandshakePacket) packetIn).getBaseIP());
            String[] split = ((ICHandshakePacket) packetIn).getBaseIP().split("\00");
            if (split.length == 3 || split.length == 4) {
                ((ICHandshakePacket) packetIn).setAddress(split[0]);
                ((INetworkManager) networkManager).setRemoteAddress(new java.net.InetSocketAddress(split[1], ((java.net.InetSocketAddress) networkManager.getRemoteAddress()).getPort()));
                ((INetworkManager) networkManager).setSpoofedUUID(UUIDTypeAdapter.fromString(split[2]));
            } else {
                ITextComponent disconnectMessage = new StringTextComponent("If you wish to use IP forwarding, please enable it in your BungeeCord config as well!");
                networkManager.sendPacket(new SDisconnectLoginPacket(disconnectMessage));
                networkManager.closeChannel(disconnectMessage);
                return;
            }
            if (split.length == 4) {
                ((INetworkManager) networkManager).setSpoofedProfile(gson.fromJson(split[3], Property[].class));
            }
        }
    }

}
