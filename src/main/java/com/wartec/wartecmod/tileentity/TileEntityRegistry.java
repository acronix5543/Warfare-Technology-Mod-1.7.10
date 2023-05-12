package com.wartec.wartecmod.tileentity;

import com.wartec.wartecmod.tileentity.launcher.TileEntityBallisticMissileLauncher;
import com.wartec.wartecmod.tileentity.launcher.TileEntityLaunchTube;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityRegistry {
	
	public static void registerTileEntities() {
		
		GameRegistry.registerTileEntity(TileEntityLaunchTube.class, com.wartec.wartecmod.lib.RefStrings.MODID + "LaunchTube");
		GameRegistry.registerTileEntity(TileEntityBallisticMissileLauncher.class, com.wartec.wartecmod.lib.RefStrings.MODID + "BallisticMissileLauncher");
		}
}
