package com.wartec.wartecmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemCruiseMissileSupersonic extends Item {
	
	public ItemCruiseMissileSupersonic (){
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		
		list.add(EnumChatFormatting.BOLD + "Speed: " + EnumChatFormatting.YELLOW + "Supersonic");
		list.add(EnumChatFormatting.BOLD + "Propulsion: " + EnumChatFormatting.YELLOW + "Ramjet");
		list.add(EnumChatFormatting.BOLD + "Size: " + EnumChatFormatting.GRAY + "60.0cm");
		list.add(EnumChatFormatting.BOLD + "Min. Range: " + EnumChatFormatting.GRAY + "250 Blocks");
		list.add(EnumChatFormatting.BOLD + "Max. Range: " + EnumChatFormatting.YELLOW + "2000 Blocks");
		list.add(EnumChatFormatting.BOLD + "Inaccuracy: " + EnumChatFormatting.YELLOW + "< 5 Blocks");
		list.add(EnumChatFormatting.BOLD + "Health: " + EnumChatFormatting.YELLOW + "7.0HP");

}
	

}