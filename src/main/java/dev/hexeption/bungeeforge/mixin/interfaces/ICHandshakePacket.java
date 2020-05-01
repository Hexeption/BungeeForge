package dev.hexeption.bungeeforge.mixin.interfaces;

/**
 * IHandshakeC2SPacket
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:17 pm
 */
public interface ICHandshakePacket {

    String getAddress();

    String getBaseIP();

    void setAddress(String address);
}
