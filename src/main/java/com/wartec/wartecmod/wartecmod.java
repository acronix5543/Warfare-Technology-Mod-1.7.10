package com.wartec.wartecmod;

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
import com.wartec.wartecmod.entity.wartecmodEntities;
import com.wartec.wartecmod.handler.WartecmodGUIHandler;
import com.wartec.wartecmod.hazard.wartecHazardRegistry;
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.packet.PacketRegistry;
import com.wartec.wartecmod.savedata.satellites.SatelliteRegistry;
import com.wartec.wartecmod.tileentity.TileEntityRegistry;
import com.wartec.wartecmod.inventory.wartecmodAssemblerRecipes;
import com.wartec.wartecmod.inventory.wartecmodShredderRecipes;
import com.wartec.wartecmod.inventory.wartecmodVanillaRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

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
				    return wartecmodItems.itemWarheadHCM;
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

		wartecmodBlocks.Blocks();
		wartecHazardRegistry.registerItems();
		TileEntityRegistry.registerTileEntities();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new WartecmodGUIHandler());

		
		
	}
	 
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		PacketRegistry.registerPackets();
		wartecmodItems.Items();

		wartecmodEntities.registerAll(this);
		
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
		SatelliteRegistry.registerAll();
		
	
	}
}

		
	
								
	 
			
				
	 
	 
	 
	
					
					
				
		
	 


	


		
	
	
	
	
	
		
	
	
			
	

	
	
		
	
	


	




