package com.wartec.wartecmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemCruiseMissileHypersonic extends Item {
	
	public ItemCruiseMissileHypersonic (){
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		
		list.add(EnumChatFormatting.BOLD + "Speed: " + EnumChatFormatting.GREEN + "Hypersonic");
		list.add(EnumChatFormatting.BOLD + "Propulsion: " + EnumChatFormatting.GREEN + "Scramjet");
		list.add(EnumChatFormatting.BOLD + "Size: " + EnumChatFormatting.GRAY + "60.0cm");
		list.add(EnumChatFormatting.BOLD + "Min. Range: " + EnumChatFormatting.GRAY + "250 Blocks");
		list.add(EnumChatFormatting.BOLD + "Max. Range: " + EnumChatFormatting.RED + "1250 Blocks");
		list.add(EnumChatFormatting.BOLD + "Inaccuracy: " + EnumChatFormatting.RED + "< 7 Blocks");
		list.add(EnumChatFormatting.BOLD + "Health: " + EnumChatFormatting.RED + "5.0HP");

}
	

}