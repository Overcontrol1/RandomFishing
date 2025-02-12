package com.overcontrol1.randomfishing;

import com.overcontrol1.randomfishing.data.RandomFishingDataLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class RandomFishing implements ModInitializer {
	public static final String MOD_ID = "randomfishing";
	public static final RegistryKey<Enchantment> ENCHANTMENT_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MOD_ID, "random_fishing"));


	public static final GameRules.Key<GameRules.IntRule> MAX_COUNT = GameRuleRegistry.register("randomFishingMaxCount",
			GameRules.Category.MISC, GameRuleFactory.createIntRule(64, 1, 64));

	public static final TagKey<Item> ITEM_BLACKLIST = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "blacklist"));

	public static boolean isBlacklisted(RegistryEntry<Item> entry) {
		final Identifier id = entry.getKey().orElseThrow().getValue();

		if (RandomFishingDataLoader.isBlacklisted(id.getNamespace()))
			return true;

		return entry.isIn(ITEM_BLACKLIST);
	}

	public static int getLevel(World world, ItemStack stack) {
		//? if <1.21 {
		/*return EnchantmentHelper.getLevel(RandomFishingOldRegistration.ENCHANTMENT, stack);
		*///?} else {
		return EnchantmentHelper.getLevel(world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(ENCHANTMENT_KEY)
				.orElseThrow(() -> new IllegalStateException("[RandomFishing] Enchantment was not registered.")), stack);
		//?}
	}

	@Override
	public void onInitialize() {
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(RandomFishingDataLoader.INSTANCE);

	}
}