package com.wartec.wartecmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemMissileAntiAirTier2 extends Item{
	
	public ItemMissileAntiAirTier2() {
		setMaxStackSize(1);
		setUnlocalizedName("ItemMissileAntiAirTier2");
	}
		
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
			
			list.add(EnumChatFormatting.GOLD + "Can be used against:");
			list.add(EnumChatFormatting.YELLOW + " Ballistic Missiles");
			

	}
}
