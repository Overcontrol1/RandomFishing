package com.overcontrol1.randomfishing;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomFishing implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "randomfishing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Enchantment ENCHANTMENT = new RandomFishingEnchantment();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Random Fishing successfully installed. ");

		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "random_fishing"), ENCHANTMENT);
	}
}