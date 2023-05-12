/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 */
package com.wartec.wartecmod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemCruiseMissileHypersonic
extends Item
implements IMissileSpawningItem {
    Class<? extends Entity> missile;

    public ItemCruiseMissileHypersonic(Class<? extends Entity> missile) {
        this.setMaxStackSize(1);
        this.missile = missile;
    }

    @Override
    public Class<? extends Entity> getMissile() {
        return missile;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add((Object)EnumChatFormatting.BOLD + "Speed: " + (Object)EnumChatFormatting.GREEN + "Hypersonic");
        list.add((Object)EnumChatFormatting.BOLD + "Propulsion: " + (Object)EnumChatFormatting.GREEN + "Scramjet");
        list.add((Object)EnumChatFormatting.BOLD + "Size: " + (Object)EnumChatFormatting.GRAY + "60.0cm");
        list.add((Object)EnumChatFormatting.BOLD + "Min. Range: " + (Object)EnumChatFormatting.GRAY + "250 Blocks");
        list.add((Object)EnumChatFormatting.BOLD + "Max. Range: " + (Object)EnumChatFormatting.RED + "1250 Blocks");
        list.add((Object)EnumChatFormatting.BOLD + "Inaccuracy: " + (Object)EnumChatFormatting.RED + "< 7 Blocks");
        list.add((Object)EnumChatFormatting.BOLD + "Health: " + (Object)EnumChatFormatting.RED + "5.0HP");
    }
}

