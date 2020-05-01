package dev.hexeption.bungeeforge.mixin.interfaces;

import com.mojang.authlib.properties.Property;
import java.net.SocketAddress;
import java.util.UUID;

/**
 * IClientConnection
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 08:58 pm
 */
public interface INetworkManager {

    void setRemoteAddress(SocketAddress socketAddress);

    UUID getSpoofedUUID();

    void setSpoofedUUID(UUID uuid);

    Property[] getSpoofedProfile();

    void setSpoofedProfile(Property[] profile);

}
