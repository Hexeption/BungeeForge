package dev.hexeption.bungeeforge;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

/**
 * MixinConnector
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 07:56 pm
 */
public class MixinConnector implements IMixinConnector {

    @Override
    public void connect() {
        Mixins.addConfiguration("mixins.bungeeforge.json");
    }
}
