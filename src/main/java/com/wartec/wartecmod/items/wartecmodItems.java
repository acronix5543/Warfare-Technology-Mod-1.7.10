 package com.wartec.wartecmod.items;

import com.hbm.items.machine.ItemSatChip;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.wartec.wartecmod.wartecmod;
import com.wartec.wartecmod.items.tool.ItemTargetFinder;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class wartecmodItems {
	
	public static void Items() 
	{
		initializeItem();
		registerItem();
	}
	
	//parts
	public static Item itemEngineInletSectionTier1;
	//PropulsionSection
	public static Item itemTurbofanEngineTier1;
	//Booster
	public static Item itemSolidBooster;
	//=
	public static Item itemCruiseMissileNoWarheadTier1;
	//Missile Parts
	public static Item itemCruiseFinsSmall;
	public static Item itemCruiseFinsBig;
	public static Item itemCruiseWings;
	public static Item itemGuidanceSystemTier1;
	public static Item itemGuidanceSystemTier2;
	public static Item itemGuidanceSystemTier3;
	public static Item itemGuidanceSystemTier4;
	public static Item itemGuidanceSystemTier5;
	public static Item itemGuidanceSystemTier6;
	//Warheads
	public static Item itemWarheadHeCM;
	public static Item itemWarheadNuclearCM;
	public static Item itemWarheadBuster;
	public static Item itemWarheadCluster;
	public static Item itemWarheadGas;
	public static Item itemWarheadNeutron;
	public static Item itemWarheadTB;
	public static Item itemWarheadHCM;
	public static Item itemWarheadEmp;
	//Some shit
	public static Item itemKKV;
	public static Item itemHWarhead;
	public static Item itemPlateU238;
	public static Item itemIngotArmorSteel;
	public static Item sat_nuclear;
	public static Item sat_emp;
	//Missiles
	public static Item itemCruiseMissileHe;
	public static Item itemCruiseMissileCluster;
	public static Item itemMissileStrongAntiBallistic;
	public static Item itemCruiseMissileNuclear;
	public static Item itemCruiseMissileH;
	public static Item itemCruiseMissileBuster;
	public static Item itemCruiseMissileTB;
	public static Item itemCruiseMissileEmp;
	public static Item itemMissileSLBM;
	public static Item itemKalibrMissile;
	public static Item itemTomahawkMissile;
	public static Item itemCj10Missile;
	public static Item itemIskanderMissile;
	public static Item itemLrhwMissile;
	public static Item itemHypersonicCruiseMissileHE;
	public static Item itemSupersonicCruiseMissileHE;
	public static Item itemHypersonicCruiseMissileNuclear;
	public static Item itemSupersonicCruiseMissileH;
	public static Item itemMissileMicroGas;
	public static Item itemMissileMicroNeutron;
	public static Item itemMissileAntiBallisticNuclear;
	public static Item itemMissileAntiAirTier1;
	public static Item itemMissileAntiAirTier2;
	public static Item itemMissileAntiAirTier3;
	//Misc
	public static Item itemMincedMeatRaw;
	public static Item itemMincedMeatCooked;
	//Tools
	public static Item itemTargetFinder;
	


	public static void initializeItem() {
		
		//parts
		itemCruiseMissileNoWarheadTier1 = new Item().setUnlocalizedName("ItemCruiseMissileNoWarheadTier1").setCreativeTab(wartecmod.tabwartecmodparts).setTextureName("wartecmod:ItemCruiseMissileNoWarheadTier1");
        itemEngineInletSectionTier1 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemEngineInletSectionTier1").setTextureName("wartecmod:ItemEngineInletSectionTier1");
		itemTurbofanEngineTier1 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemTurbofanEngineTier1").setTextureName("wartecmod:ItemTurbofanEngineTier1");
		itemSolidBooster = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemSolidBooster").setTextureName("wartecmod:ItemSolidBooster");
		itemWarheadHeCM = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadHeCM").setTextureName("wartecmod:ItemWarheadHeCM");
		itemWarheadCluster = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadCluster").setTextureName("wartecmod:ItemWarheadCluster");
		itemWarheadBuster = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadBuster").setTextureName("wartecmod:ItemWarheadBuster");
		itemWarheadGas = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadGas").setTextureName("wartecmod:ItemWarheadGas");
		itemWarheadNeutron = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadNeutron").setTextureName("wartecmod:ItemWarheadNeutron");
		itemWarheadNuclearCM = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadNuclearCM").setTextureName("wartecmod:ItemWarheadNuclearCM");
		itemWarheadHCM = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadHCM").setTextureName("wartecmod:ItemWarheadHCM");
		itemWarheadTB = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadFAE").setTextureName("wartecmod:ItemWarheadFAE");
		itemWarheadEmp = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemWarheadEmp").setTextureName("wartecmod:ItemWarheadEmp");
		itemKKV = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemKKV").setTextureName("wartecmod:ItemKKV");
		itemHWarhead = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemHWarhead").setTextureName("wartecmod:ItemHWarhead");
		itemGuidanceSystemTier1 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemGuidanceSystemTier1").setTextureName("wartecmod:ItemGuidanceSystemTier1");
		itemGuidanceSystemTier2 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemGuidanceSystemTier2").setTextureName("wartecmod:ItemGuidanceSystemTier2");
		itemGuidanceSystemTier3 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemGuidanceSystemTier3").setTextureName("wartecmod:ItemGuidanceSystemTier3");
		itemGuidanceSystemTier4 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemGuidanceSystemTier4").setTextureName("wartecmod:ItemGuidanceSystemTier4");
		itemGuidanceSystemTier5 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemGuidanceSystemTier5").setTextureName("wartecmod:ItemGuidanceSystemTier5");
		itemGuidanceSystemTier6 = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemGuidanceSystemTier6").setTextureName("wartecmod:ItemGuidanceSystemTier6");
		itemPlateU238 = new Item().setUnlocalizedName("ItemPlateU238").setCreativeTab(wartecmod.tabwartecmodparts).setTextureName("wartecmod:ItemPlateU238");
		itemIngotArmorSteel = new Item().setUnlocalizedName("IngotArmorSteel").setCreativeTab(wartecmod.tabwartecmodparts).setTextureName("wartecmod:ItemIngotArmorSteel");
		itemCruiseFinsSmall = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemCruiseFinsSmall").setTextureName("wartecmod:ItemCruiseFinsSmall");
		itemCruiseFinsBig = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemCruiseFinsBig").setTextureName("wartecmod:ItemCruiseFinsBig");
		itemCruiseWings = new Item().setCreativeTab(wartecmod.tabwartecmodparts).setMaxStackSize(64).setUnlocalizedName("ItemCruiseWings").setTextureName("wartecmod:ItemCruiseWings");
		
		//Missiles
		itemCruiseMissileHe = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileHe").setTextureName("wartecmod:ItemCruiseMissileHe");
		itemCruiseMissileEmp = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileEmp").setTextureName("wartecmod:ItemCruiseMissileEmp");
		itemCruiseMissileNuclear = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileNuclear").setTextureName("wartecmod:ItemCruiseMissileNuclear");
		itemCruiseMissileH = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileH").setTextureName("wartecmod:ItemCruiseMissileH");
		itemCruiseMissileCluster = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileCluster").setTextureName("wartecmod:ItemCruiseMissileCluster");
		itemKalibrMissile = new ItemKalibrMissile().setCreativeTab(wartecmod.tabwartecmodcruisemissiles);
		itemCj10Missile = new ItemCj10Missile().setCreativeTab(wartecmod.tabwartecmodcruisemissiles);
		itemTomahawkMissile = new ItemTomahawkMissile().setCreativeTab(wartecmod.tabwartecmodcruisemissiles);
		itemCruiseMissileBuster = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileBuster").setTextureName("wartecmod:ItemCruiseMissileBuster");
		itemCruiseMissileEmp = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileEmp").setTextureName("wartecmod:ItemCruiseMissileEmp");
		itemCruiseMissileTB = new ItemCruiseMissileSubsonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemCruiseMissileFAE").setTextureName("wartecmod:ItemCruiseMissileFAE");
		itemMissileSLBM = new Item().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemMissileSLBM").setTextureName("wartecmod:ItemMissileSLBM");
		itemIskanderMissile = new ItemIskanderMissile().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemIskanderMissile").setTextureName("wartecmod:ItemIskanderMissile");
		itemLrhwMissile = new Item().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemLrhwMissile").setTextureName("wartecmod:ItemLrhwMissile");
		itemHypersonicCruiseMissileHE = new ItemCruiseMissileHypersonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemHypersonicCruiseMissileHE").setTextureName("wartecmod:ItemHypersonicCruiseMissileHe");
		itemSupersonicCruiseMissileHE = new ItemCruiseMissileSupersonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemSupersonicCruiseMissileHE").setTextureName("wartecmod:ItemSupersonicCruiseMissileHe");
		itemHypersonicCruiseMissileNuclear = new ItemCruiseMissileHypersonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemHypersonicCruiseMissileNuclear").setTextureName("wartecmod:ItemHypersonicCruiseMissileNuclear");
		itemSupersonicCruiseMissileH = new ItemCruiseMissileSupersonic().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setUnlocalizedName("ItemSupersonicCruiseMissileH").setTextureName("wartecmod:ItemSupersonicCruiseMissileH");
		itemMissileMicroGas = new Item().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemMissileMicroGas").setTextureName("wartecmod:ItemMissileMicroGas");
		itemMissileMicroNeutron = new Item().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemMissileMicroNeutron").setTextureName("wartecmod:ItemMissileMicroNeutron");
		itemMissileAntiBallisticNuclear = new Item().setMaxStackSize(1).setUnlocalizedName("ItemMissileAntiBallisticNuclear").setTextureName("wartecmod:ItemMissileAntiBallisticNuclear");
		itemMissileAntiAirTier1 = new ItemMissileAntiAirTier1().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemMissileAntiAirTier1").setTextureName("wartecmod:ItemMissileAntiAirTier1");
		itemMissileAntiAirTier2 = new ItemMissileAntiAirTier2().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemMissileAntiAirTier2").setTextureName("wartecmod:ItemMissileAntiAirTier2");
		itemMissileAntiAirTier3 = new ItemMissileAntiAirTier3().setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setMaxStackSize(1).setUnlocalizedName("ItemMissileAntiAirTier3").setTextureName("wartecmod:ItemMissileAntiAirTier3");
		
		
		//Misc
		itemMincedMeatRaw = new ItemFood(1, 0, false).setUnlocalizedName("ItemMincedMeatRaw").setCreativeTab(wartecmod.tabwartecmodcons).setTextureName("wartecmod:ItemMincedMeatRaw");
		itemMincedMeatCooked = new ItemFood(4, 0, false).setUnlocalizedName("ItemMincedMeatCooked").setCreativeTab(wartecmod.tabwartecmodcons).setTextureName("wartecmod:ItemMincedMeatCooked");
		sat_nuclear = new ItemSatChip().setUnlocalizedName("sat_nuclear").setMaxStackSize(1).setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setTextureName(com.wartec.wartecmod.lib.RefStrings.MODID + ":ItemSatelliteNuclear");
		sat_emp = new ItemSatChip().setUnlocalizedName("sat_emp").setMaxStackSize(1).setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setTextureName(RefStrings.MODID + ":sat_mapper");
		
		//Tools
		itemTargetFinder = new ItemTargetFinder().setUnlocalizedName("ItemTargetFinder").setCreativeTab(wartecmod.tabwartecmodcruisemissiles).setTextureName("wartecmod:ItemTargetFinder");
		
	}

	private static void registerItem() {
		
		//parts
		GameRegistry.registerItem(itemCruiseMissileNoWarheadTier1, "ItemCruiseMissileNoWarheadTier1");
		GameRegistry.registerItem(itemEngineInletSectionTier1, "ItemEngineInletSectionTier1");
		GameRegistry.registerItem(itemTurbofanEngineTier1, "ItemTurbofanEngineTier1");
        GameRegistry.registerItem(itemSolidBooster, "ItemSolidBooster");
		GameRegistry.registerItem(itemWarheadHeCM, "ItemWarheadHeCM");
		GameRegistry.registerItem(itemWarheadCluster, "ItemWarheadCluster");
		GameRegistry.registerItem(itemWarheadBuster, "ItemWarheadBuster");
		GameRegistry.registerItem(itemWarheadEmp, "ItemWarheadEmp");
		GameRegistry.registerItem(itemWarheadTB, "ItemWarheadTB");
		GameRegistry.registerItem(itemWarheadNuclearCM, "ItemWarheadNuclearCM");
		GameRegistry.registerItem(itemWarheadHCM, "ItemWarheadHCM");
		GameRegistry.registerItem(itemKKV,  "ItemKKV");
		GameRegistry.registerItem(itemHWarhead, "ItemHWarhead");
		GameRegistry.registerItem(itemWarheadGas, "ItemWarheadGas");
		GameRegistry.registerItem(itemWarheadNeutron, "ItemWarheadNeutron");
		GameRegistry.registerItem(itemGuidanceSystemTier1, "ItemGuidanceSystemTier1");
		GameRegistry.registerItem(itemGuidanceSystemTier2, "ItemGuidanceSystemTier2");
		GameRegistry.registerItem(itemGuidanceSystemTier3, "ItemGuidanceSystemTier3");
	    GameRegistry.registerItem(itemGuidanceSystemTier4, "ItemGuidanceSystemTier4");
		GameRegistry.registerItem(itemGuidanceSystemTier5, "ItemGuidanceSystemTier5");
		GameRegistry.registerItem(itemGuidanceSystemTier6, "ItemGuidanceSystemTier6");
		GameRegistry.registerItem(itemCruiseFinsSmall, "ItemCruiseFinsSmall");
	    GameRegistry.registerItem(itemCruiseFinsBig, "ItemCruiseFinsBig");	
		GameRegistry.registerItem(itemCruiseWings, "ItemCruiseWings");
		GameRegistry.registerItem(itemPlateU238, itemPlateU238.getUnlocalizedName());
		GameRegistry.registerItem(itemIngotArmorSteel, "ItemIngotArmorSteel");
		
		
		
		//Missiles
		GameRegistry.registerItem(itemCruiseMissileHe, "ItemCruiseMissileHe");
		GameRegistry.registerItem(itemCruiseMissileCluster, "ItemCruiseMissileCluster");
		GameRegistry.registerItem(itemCruiseMissileBuster, "ItemCruiseMissileBuster");
		GameRegistry.registerItem(itemCruiseMissileEmp, "ItemCruiseMissileEmp");
		GameRegistry.registerItem(itemCruiseMissileTB, "ItemCruiseMissileTB");
		GameRegistry.registerItem(itemCruiseMissileNuclear, "ItemCruiseMissileNuclear");
		GameRegistry.registerItem(itemCruiseMissileH, "ItemCruiseMissileH");
		GameRegistry.registerItem(itemSupersonicCruiseMissileHE, "ItemSupersonicCruiseMissileHE");
		GameRegistry.registerItem(itemSupersonicCruiseMissileH, "ItemSupersonicCruiseMissileH");
		GameRegistry.registerItem(itemHypersonicCruiseMissileHE, "ItemHypersonicCruiseMissileHE");
		GameRegistry.registerItem(itemHypersonicCruiseMissileNuclear, "ItemHypersonicCruiseMissileNuclear");
		GameRegistry.registerItem(itemLrhwMissile, "ItemLrhwMissile");
		GameRegistry.registerItem(itemMissileSLBM, "ItemMissileSLBM");
		GameRegistry.registerItem(itemMissileMicroGas, "ItemMissileMicroGas");
		GameRegistry.registerItem(itemMissileMicroNeutron, "ItemMissileMicroNeutron");
		GameRegistry.registerItem(itemMissileAntiAirTier1, "ItemMissileAntiAirTier1");
		GameRegistry.registerItem(itemMissileAntiAirTier2, "ItemMissileAntiAirTier2");
		GameRegistry.registerItem(itemMissileAntiAirTier3, "ItemMissileAntiAirTier3");
		GameRegistry.registerItem(itemMissileAntiBallisticNuclear, "ItemMissileAntiBallisticNuclear");
		GameRegistry.registerItem(itemTomahawkMissile, "ItemTomahawkMissile");
		GameRegistry.registerItem(itemKalibrMissile, "ItemKalibrMissile");
		GameRegistry.registerItem(itemCj10Missile, "ItemCj10Missile");
		GameRegistry.registerItem(itemIskanderMissile, "ItemIskanderMissile");
		
		
		//Misc
		GameRegistry.registerItem(itemMincedMeatRaw, itemMincedMeatRaw.getUnlocalizedName());
		GameRegistry.registerItem(itemMincedMeatCooked, itemMincedMeatCooked.getUnlocalizedName());
		GameRegistry.registerItem(sat_nuclear, sat_nuclear.getUnlocalizedName());
		GameRegistry.registerItem(sat_emp, sat_emp.getUnlocalizedName());
		//Tool
		GameRegistry.registerItem(itemTargetFinder, itemTargetFinder.getUnlocalizedName());
		
	}
}
	
	


