package dev.hexeption.bungeeforge.config;

import net.minecraftforge.fml.config.ModConfig;

/**
 * ConfigHelper
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:58 pm
 */
public class ConfigHelper {

    public static void bakeServer(final ModConfig config) {
        BFConfig.bungeeCord = ConfigHolder.SERVER.serverBungeeCord.get();
        BFConfig.velocity = ConfigHolder.SERVER.serverVelocity.get();
        BFConfig.secret = ConfigHolder.SERVER.serverSecret.get();
    }


}
