package dev.hexeption.bungeeforge.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ConfigHolder
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 09:59 pm
 */
public class ConfigHolder {

    public static final ForgeConfigSpec SERVER_SPEC;
    static final ServerConfig SERVER;

    static {
        {
            final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
            SERVER = specPair.getLeft();
            SERVER_SPEC = specPair.getRight();
        }
    }

}
