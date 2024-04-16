package com.overcontrol1.randomfishing;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RandomFishing implements ModInitializer {
	public static final String MOD_ID = "randomfishing";
	public static final Enchantment ENCHANTMENT = new RandomFishingEnchantment();

	@Override
	public void onInitialize() {
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "random_fishing"), ENCHANTMENT);
	}
}