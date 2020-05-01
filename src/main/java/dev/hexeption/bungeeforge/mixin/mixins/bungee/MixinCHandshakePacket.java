package dev.hexeption.bungeeforge.mixin.mixins.bungee;

import dev.hexeption.bungeeforge.config.BFConfig;
import dev.hexeption.bungeeforge.config.ConfigHelper;
import dev.hexeption.bungeeforge.config.ConfigHolder;
import dev.hexeption.bungeeforge.mixin.interfaces.ICHandshakePacket;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.client.CHandshakePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * MixinCHandshakePacket
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:20 pm
 */
@Mixin(CHandshakePacket.class)
public class MixinCHandshakePacket implements ICHandshakePacket {

    @Shadow
    private String ip;

    private String baseIP;

    @Inject(method = "readPacketData", at = @At("HEAD"))
    private void addBaseIP(PacketBuffer buf, CallbackInfo ci) {
        this.baseIP = buf.readString(Short.MAX_VALUE);
    }

    @Redirect(method = "readPacketData", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;readString(I)Ljava/lang/String;"))
    public String readPacketData(PacketBuffer packetBuffer, int maxLength) {
        if (!BFConfig.bungeeCord) {
            return packetBuffer.readString(255);
        }

        return packetBuffer.readString(Short.MAX_VALUE);
    }


    @Override
    public String getAddress() {
        return ip;
    }

    @Override
    public void setAddress(String address) {
        this.ip = address;
    }

    @Override
    public String getBaseIP() {
        return baseIP;
    }
}
