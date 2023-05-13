package com.wartec.wartecmod.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.gui.GuiInfoContainer;
import com.wartec.wartecmod.inventory.container.ContainerLaunchTube;
import com.wartec.wartecmod.tileentity.vls.TileEntityVlsLaunchTube;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUILaunchTube extends GuiInfoContainer {
	
	private static ResourceLocation texture = new ResourceLocation(com.wartec.wartecmod.lib.RefStrings.MODID + ":textures/gui/weapon/gui_launch_tube.png");
	private TileEntityVlsLaunchTube diFurnace;

	public GUILaunchTube(InventoryPlayer invPlayer, TileEntityVlsLaunchTube tedf) {
		super(new ContainerLaunchTube(invPlayer, tedf));
		diFurnace = tedf;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 8, guiTop + 53, 160, 16, diFurnace.power, diFurnace.getMaxPower());

		String[] text = new String[] { "First Slot:",
				"  -Cruise Missile"};
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		
		String[] text1 = new String[] { "Second Slot:",
				"  -Target designator for missiles"};
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36 + 16, 16, 16, guiLeft - 8, guiTop + 36 + 16, text1);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.diFurnace.hasCustomInventoryName() ? this.diFurnace.getInventoryName() : I18n.format(this.diFurnace.getInventoryName());
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int j1 = (int)diFurnace.getPowerScaled(160);
		drawTexturedModalRect(guiLeft + 8, guiTop + 53, 8, 166, j1, 16);

		this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 2);
		this.drawInfoPanel(guiLeft - 16, guiTop + 36 + 16, 16, 16, 3);
	}
}