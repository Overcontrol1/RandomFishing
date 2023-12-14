package com.overcontrol1.randomfishing.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.overcontrol1.randomfishing.RandomFishing;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LootTable.class)
public class MixinLootTable {
    @Shadow @Final private LootContextType type;

    @ModifyReturnValue(method = "generateLoot(Lnet/minecraft/loot/context/LootContextParameterSet;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", at = @At("RETURN"))
    public ObjectArrayList<ItemStack> randomfishing$overrideFishingLootIfEnchantmentPresent(ObjectArrayList<ItemStack> original, LootContextParameterSet parameterSet) {
        if (this.type != LootContextTypes.FISHING) {
            return original;
        }

        ItemStack usedItem = parameterSet.getOptional(LootContextParameters.TOOL);
        Entity bobberEntity = parameterSet.getOptional(LootContextParameters.THIS_ENTITY);

        if (usedItem == null || bobberEntity == null) {
            return original;
        }

        if (EnchantmentHelper.getLevel(RandomFishing.ENCHANTMENT, usedItem) < 1) {
            return original;
        }

        World world = bobberEntity.getWorld();

        Item item = Registries.ITEM.getRandom(world.random).map(RegistryEntry.Reference::value).orElse(null);
        assert item != null;
        ItemStack stack = new ItemStack(item, world.random.nextBetween(1, item.getMaxCount()));

        return ObjectArrayList.of(stack);
    }
}
