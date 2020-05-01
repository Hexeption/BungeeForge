package dev.hexeption.bungeeforge.mixin.mixins.bungee;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.hexeption.bungeeforge.mixin.interfaces.INetworkManager;
import java.util.Arrays;
import java.util.UUID;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.ServerLoginNetHandler;
import net.minecraft.network.login.client.CLoginStartPacket;
import net.minecraft.server.MinecraftServer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * MixinServerLoginNetHandler
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 10:33 pm
 */
@Mixin(ServerLoginNetHandler.class)
public class MixinServerLoginNetHandler {

    @Shadow @Final private MinecraftServer server;

    @Shadow @Final public NetworkManager networkManager;

    @Shadow private GameProfile loginGameProfile;

    @Inject(method = "processLoginStart", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, ordinal = 0, shift = At.Shift.AFTER, target = "Lnet/minecraft/network/login/ServerLoginNetHandler;loginGameProfile:Lcom/mojang/authlib/GameProfile;"))
    private void onProcessLoginStart(CLoginStartPacket packetIn, CallbackInfo ci) {
        if (!this.server.isServerInOnlineMode()) {
            UUID uuid;
            if (((INetworkManager) networkManager).getSpoofedUUID() != null) {
                uuid = ((INetworkManager) networkManager).getSpoofedUUID();
            } else {
                uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.loginGameProfile.getName()).getBytes(Charsets.UTF_8));
            }

            this.loginGameProfile = new GameProfile(uuid, this.loginGameProfile.getName());

            if (((INetworkManager) networkManager).getSpoofedProfile() != null) {
                for (Property property : ((INetworkManager) networkManager).getSpoofedProfile()) {
                    this.loginGameProfile.getProperties().put(property.getName(), property);
                }
            }
        }
    }

}
