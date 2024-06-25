package com.overcontrol1.randomfishing;

import com.overcontrol1.randomfishing.data.RandomFishingDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class RandomFishing implements ModInitializer {
	public static final String MOD_ID = "randomfishing";
	public static final Enchantment ENCHANTMENT = new RandomFishingEnchantment();

	public static final GameRules.Key<GameRules.IntRule> MAX_COUNT = GameRuleRegistry.register("randomFishingMaxCount",
			GameRules.Category.MISC, GameRuleFactory.createIntRule(64, 1, 64));

	public static final TagKey<Item> ITEM_BLACKLIST = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "blacklist"));

	public static boolean isBlacklisted(RegistryEntry<Item> entry) {
		final Identifier id = entry.getKey().orElseThrow().getValue();

		if (RandomFishingDataLoader.isBlacklisted(id.getNamespace()))
			return true;

		return entry.isIn(ITEM_BLACKLIST);
	}

	@Override
	public void onInitialize() {
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(RandomFishingDataLoader.INSTANCE);

		Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "random_fishing"), ENCHANTMENT);
	}
}