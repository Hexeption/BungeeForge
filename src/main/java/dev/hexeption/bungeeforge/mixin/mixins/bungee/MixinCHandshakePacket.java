package dev.hexeption.bungeeforge.mixin.mixins.bungee;

import dev.hexeption.bungeeforge.config.BFConfig;
import dev.hexeption.bungeeforge.config.ConfigHelper;
import dev.hexeption.bungeeforge.config.ConfigHolder;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.client.CHandshakePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * MixinCHandshakePacket
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:20 pm
 */
@Mixin(CHandshakePacket.class)
public class MixinCHandshakePacket {

    @Redirect(method = "readPacketData", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;readString(I)Ljava/lang/String;"))
    public String readPacketData(PacketBuffer packetBuffer, int maxLength) {
        if (!BFConfig.bungeeCord) {
            return packetBuffer.readString(255);
        }
        return packetBuffer.readString(Short.MAX_VALUE);
    }


}
