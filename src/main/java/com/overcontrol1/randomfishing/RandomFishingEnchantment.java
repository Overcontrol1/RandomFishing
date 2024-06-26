package com.overcontrol1.randomfishing;

import eu.pb4.polymer.core.api.other.PolymerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class RandomFishingEnchantment extends Enchantment implements PolymerEnchantment {
    public static final EquipmentSlot[] VALID_SLOTS = new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
    protected RandomFishingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.FISHING_ROD, VALID_SLOTS);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return !(other instanceof LuckEnchantment) && this != other;
    }
}