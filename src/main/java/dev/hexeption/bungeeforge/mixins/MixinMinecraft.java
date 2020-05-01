package dev.hexeption.bungeeforge.mixins;

import dev.hexeption.bungeeforge.Bungeeforge;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * MixinMinecraft
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 01/05/2020 - 08:00 pm
 */
@Mixin(DedicatedServer.class)
public class MixinMinecraft {

    @Inject(method = "init", at = @At("RETURN"))
    public void ontick(CallbackInfoReturnable<Boolean> cir) {
        System.out.println("Testing");
    }

}
