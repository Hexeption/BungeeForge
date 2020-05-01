package dev.hexeption.bungeeforge.mixin.mixins;

import com.mojang.authlib.properties.Property;
import dev.hexeption.bungeeforge.mixin.interfaces.INetworkManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.SocketAddress;
import java.util.UUID;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * NetworkManager
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:00 pm
 */
@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager extends SimpleChannelInboundHandler implements INetworkManager {

    @Shadow private SocketAddress socketAddress;

    private UUID spoofedUUID;
    private Property[] spoofedProfile;

    @Override
    public void setRemoteAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    @Override
    public UUID getSpoofedUUID() {
        return spoofedUUID;
    }

    @Override
    public void setSpoofedUUID(UUID uuid) {
        this.spoofedUUID = uuid;
    }

    @Override
    public Property[] getSpoofedProfile() {
        return spoofedProfile;
    }

    @Override
    public void setSpoofedProfile(Property[] profile) {
        this.spoofedProfile = profile;
    }
}
