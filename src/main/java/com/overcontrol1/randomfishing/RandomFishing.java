package com.overcontrol1.randomfishing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class RandomFishing implements ModInitializer {
	public static final String MOD_ID = "randomfishing";
	public static final Enchantment ENCHANTMENT = new RandomFishingEnchantment();

	public static final GameRules.Key<GameRules.IntRule> MAX_COUNT = GameRuleRegistry.register("randomFishingMaxCount",
			GameRules.Category.MISC, GameRuleFactory.createIntRule(64, 1, 64));


	@Override
	public void onInitialize() {
		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "random_fishing"), ENCHANTMENT);
	}
}