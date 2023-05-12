package com.wartec.wartecmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemIskanderMissile extends Item{
	
	public ItemIskanderMissile() {
		setMaxStackSize(1);
		setUnlocalizedName("ItemIskanderMissile");
	}
		
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
			
			list.add(EnumChatFormatting.BOLD + "Warhead: " + EnumChatFormatting.YELLOW + "HE");
			list.add(EnumChatFormatting.BOLD + "Strength: " + EnumChatFormatting.GRAY + "40.0");
			list.add(EnumChatFormatting.BOLD + "Size: " + EnumChatFormatting.GRAY + "7.30m");

	}
}
