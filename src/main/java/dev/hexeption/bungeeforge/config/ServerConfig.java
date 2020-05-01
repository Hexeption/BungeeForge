package dev.hexeption.bungeeforge.config;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * ServerConfig
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:59 pm
 */
final class ServerConfig {

    final ForgeConfigSpec.BooleanValue serverBungeeCord;
    final ForgeConfigSpec.BooleanValue serverVelocity;
    final ForgeConfigSpec.ConfigValue<String> serverSecret;

    ServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        serverBungeeCord = builder
            .comment("Enable BungeeCord")
            .translation("bungeeforge.config.serverBungeeCord")
            .define("serverBungeeCord", false);
        serverVelocity = builder
            .comment("Enable Velocity")
            .translation("bungeeforge.config.serverVelocity")
            .define("serverVelocity", false);
        serverSecret = builder
            .comment("Velocity proxy secret")
            .translation("bungeeforge.config.serverSecret")
            .define("serverSecret", "");
        builder.pop();
    }

}
