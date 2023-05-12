package com.wartec.wartecmod;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.missile.EntityMissileNuclear;
import com.hbm.handler.GUIHandler;
import com.hbm.hazard.HazardRegistry;
import com.hbm.inventory.recipes.AssemblerRecipes;
import com.hbm.inventory.recipes.MachineRecipes;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.OreDictStack;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemAssemblyTemplate;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.wartec.wartecmod.Proxies.wartecmodProxy;
import com.wartec.wartecmod.blocks.wartecmodBlocks;
import com.wartec.wartecmod.entity.missile.EntityCJ10Missile;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileBuster;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileCluster;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileEmp;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileH;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileHE;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileNuclear;
import com.wartec.wartecmod.entity.missile.EntityHypersonicCruiseMissileNuclear;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileTB;
import com.wartec.wartecmod.entity.missile.EntityHypersonicCruiseMissileHE;
import com.wartec.wartecmod.entity.missile.EntityIskanderMissile;
import com.wartec.wartecmod.entity.missile.EntityKalibrMissile;
import com.wartec.wartecmod.entity.missile.EntityLrhwMissile;
import com.wartec.wartecmod.entity.missile.EntityMissileAntiAirTier1;
import com.wartec.wartecmod.entity.missile.EntityMissileAntiBallisticNuclear;
import com.wartec.wartecmod.entity.missile.EntityMissileMicroGas;
import com.wartec.wartecmod.entity.missile.EntityMissileMicroNeutron;
import com.wartec.wartecmod.entity.missile.EntityMissileSlbm;
import com.wartec.wartecmod.entity.missile.EntitySatelliteMissileNuclear;
import com.wartec.wartecmod.entity.missile.EntitySupersonicCruiseMissileHE;
import com.wartec.wartecmod.entity.missile.EntitySupersonicCruiseMissileH;
import com.wartec.wartecmod.entity.missile.EntityTomahawkMissile;
import com.wartec.wartecmod.handler.WartecmodGUIHandler;
import com.wartec.wartecmod.hazard.wartecHazardRegistry;
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.packet.PacketRegistry;
import com.wartec.wartecmod.savedata.satellites.SatelliteNuclear;
import com.wartec.wartecmod.savedata.satellites.SatelliteRegistry;
import com.wartec.wartecmod.tileentity.TileEntityRegistry;
import com.wartec.wartecmod.tileentity.launcher.TileEntityLaunchTube;
import com.wartec.wartecmod.inventory.wartecmodAssemblerRecipes;
import com.wartec.wartecmod.inventory.wartecmodShredderRecipes;
import com.wartec.wartecmod.inventory.wartecmodVanillaRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import scala.tools.nsc.doc.model.Public;

@Mod(modid = com.wartec.wartecmod.lib.RefStrings.MODID, name = com.wartec.wartecmod.lib.RefStrings.NAME, version = com.wartec.wartecmod.lib.RefStrings.VERSION)
public class wartecmod {
	
	@Instance(com.wartec.wartecmod.lib.RefStrings.MODID)
	public static wartecmod instance;
	  
	@SidedProxy(clientSide="com.wartec.wartecmod.Proxies.wartecmodClientProxy",serverSide="com.wartec.wartecmod.Proxies.wartecmodProxy")
	public static wartecmodProxy proxy;
	
	//ConfigFile
	public static Configuration config;
	
	//Creative Tabs
		public static CreativeTabs tabwartecmodcruisemissiles = new CreativeTabs("tabwartecmodcruisemissiles") {
				@Override
				@SideOnly(Side.CLIENT)
				public Item getTabIconItem() {
					return wartecmodItems.itemCruiseMissileHe;
				}
		};
		public static CreativeTabs tabwartecmodparts = new CreativeTabs("tabwartecmodparts") {
			    @Override
			    @SideOnly(Side.CLIENT)
			    public Item getTabIconItem() {
				    return wartecmodItems.itemGuidanceSystemTier4;
			    }
		};
		public static CreativeTabs tabwartecmodblocks = new CreativeTabs("tabwartecmodblocks") {
		    @Override
		    @SideOnly(Side.CLIENT)
		    public Item getTabIconItem() {
		    	
		    	if(wartecmodBlocks.BlockArmorSteel != null)
				{
					return Item.getItemFromBlock(wartecmodBlocks.BlockArmorSteel);
				}
		    	return Items.iron_pickaxe;
		    }
	    };
	    public static CreativeTabs tabwartecmodgear = new CreativeTabs("tabwartecmodgear") {
	    	@Override
		    @SideOnly(Side.CLIENT)
		    public Item getTabIconItem() {
			    return Items.arrow;
		    }
	    };
	    public static CreativeTabs tabwartecmodcons = new CreativeTabs("tabwartecmodcons") {
	    	@Override
		    @SideOnly(Side.CLIENT)
		    public Item getTabIconItem() {
			    return wartecmodItems.itemMincedMeatRaw;
		    }
	    };
	    
	@EventHandler
		public void preload(FMLInitializationEvent event) {
		
		wartecmodItems.Items();
		wartecmodBlocks.Blocks();
		wartecHazardRegistry.registerItems();
		TileEntityRegistry.registerTileEntities();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new WartecmodGUIHandler());
		
		
	}
	 
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		PacketRegistry.registerPackets();
		//Entities
		EntityRegistry.registerModEntity(EntityMissileSlbm.class, "entity_slbm_Missile", 1, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileHE.class, "entity_Cruise_Missile", 2, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileH.class, "entity_Cruise_Missile_H", 3, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileNuclear.class, "entity_Cruise_Missile_Nuclear", 4, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileTB.class, "entity_Cruise_Missile_FAE", 6, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileCluster.class, "entity_Cruise_Missile_Cluster", 7, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileBuster.class, "entity_Cruise_Missile_Buster", 8, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCruiseMissileEmp.class, "entity_Cruise_Missile_Emp", 9, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityHypersonicCruiseMissileHE.class, "entity_Hypersonic_Cruise_Missile", 10, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityIskanderMissile.class, "entity_Iskander_Missile", 11, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityLrhwMissile.class, "entity_Lrhw_Missile", 12, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntitySupersonicCruiseMissileHE.class, "entity_Supersonic_Cruise_Missile", 13, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityTomahawkMissile.class, "entity_Tomahawk_Missile", 14, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityKalibrMissile.class, "entity_Kalibr_Missile", 15, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntitySupersonicCruiseMissileH.class, "entity_Supersonic_Cruise_Missile_Nuclear", 16, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityHypersonicCruiseMissileNuclear.class, "entity_Hypersonic_Cruise_Missile_H", 17, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityMissileMicroGas.class, "entity_Missile_Micro_Gas", 18, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityMissileMicroNeutron.class, "entity_Missile_Micro_Neutron", 20, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityMissileAntiBallisticNuclear.class, "entity_Missile_Anti_Ballistic_Nuclear", 19, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityMissileAntiAirTier1.class, "entity_Missile_Anti_Air_Tier1", 21, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntityCJ10Missile.class, "entity_CJ10_Missile", 22, this, 1000, 1, true);
		EntityRegistry.registerModEntity(EntitySatelliteMissileNuclear.class, "entity_Satellite_Missile_Nuclear", 23, this, 1000, 1, true);
		
		
		config = new Configuration(event.getSuggestedConfigurationFile());

	}
	
	
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		wartecmodAssemblerRecipes.AssemblerRecipes();
		wartecmodShredderRecipes.Shredderrecipes();
		wartecmodVanillaRecipes.VanillaRecipes();
		proxy.registerRenderers();
		SatelliteRegistry.main(new String[]{});
		
	
	}
}

		
	
								
	 
			
				
	 
	 
	 
	
					
					
				
		
	 


	


		
	
	
	
	
	
		
	
	
			
	

	
	
		
	
	


	




