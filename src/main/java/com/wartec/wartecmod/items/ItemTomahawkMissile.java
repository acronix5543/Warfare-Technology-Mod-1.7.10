package com.wartec.wartecmod.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemTomahawkMissile extends Item {
	
	public ItemTomahawkMissile() {
		setMaxStackSize(1);
		setUnlocalizedName("ItemTomahawkMissile");

	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		
		list.add(EnumChatFormatting.BOLD + "Warhead: " + EnumChatFormatting.YELLOW + "HE");
		list.add(EnumChatFormatting.BOLD + "Strength: " + EnumChatFormatting.GRAY + "25.0");
		list.add(EnumChatFormatting.BOLD + "Size: " + EnumChatFormatting.GRAY + "6.25m");
		list.add(EnumChatFormatting.BOLD + "Speed: " + EnumChatFormatting.RED + "Subsonic");
		list.add(EnumChatFormatting.BOLD + "Propulsion: " + EnumChatFormatting.RED + "Turbojet");
		list.add(EnumChatFormatting.BOLD + "Size: " + EnumChatFormatting.GRAY + "51.8cm");
		list.add(EnumChatFormatting.BOLD + "Min. Range: " + EnumChatFormatting.GRAY + "250 Blocks");
		list.add(EnumChatFormatting.BOLD + "Max. Range: " + EnumChatFormatting.GREEN + "3500 Blocks");
		list.add(EnumChatFormatting.BOLD + "Health: " + EnumChatFormatting.GREEN + "10.0HP");

}
}