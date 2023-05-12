package com.wartec.wartecmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemCruiseMissileSubsonic extends Item {
	
	public ItemCruiseMissileSubsonic (){
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		
		list.add(EnumChatFormatting.BOLD + "Speed: " + EnumChatFormatting.RED + "Subsonic");
		list.add(EnumChatFormatting.BOLD + "Propulsion: " + EnumChatFormatting.RED + "Turbojet");
		list.add(EnumChatFormatting.BOLD + "Size: " + EnumChatFormatting.GRAY + "51.8cm");
		list.add(EnumChatFormatting.BOLD + "Min. Range: " + EnumChatFormatting.GRAY + "250 Blocks");
		list.add(EnumChatFormatting.BOLD + "Max. Range: " + EnumChatFormatting.GREEN + "3500 Blocks");
		list.add(EnumChatFormatting.BOLD + "Inaccuracy: " + EnumChatFormatting.GREEN + "< 3 Blocks");
		list.add(EnumChatFormatting.BOLD + "Health: " + EnumChatFormatting.GREEN + "10.0HP");

}
	

}
