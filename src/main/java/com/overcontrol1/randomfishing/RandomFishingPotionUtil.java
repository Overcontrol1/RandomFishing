package com.overcontrol1.randomfishing;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;

//? if <1.21 {
/*import net.minecraft.potion.PotionUtil;
*///?} else {
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
//?}

public final class RandomFishingPotionUtil {
    public static boolean canApplyPotion(ItemStack stack) {
        //? if <1.21 {
        /*return stack.getItem() instanceof PotionItem || stack.getItem() instanceof TippedArrowItem;
        *///?} else {
        return stack.contains(DataComponentTypes.POTION_CONTENTS);
        //?}
    }

    public static void setPotion(ItemStack stack, RegistryEntry<Potion> potion) {
        //? if <1.21 {
        /*PotionUtil.setPotion(stack, potion.value());
        *///?} else {
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potion));
        //?}
    }
}
