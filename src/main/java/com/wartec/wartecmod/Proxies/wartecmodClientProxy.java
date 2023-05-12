package com.wartec.wartecmod.Proxies;


import com.hbm.entity.missile.EntityMissileNuclear;
import com.hbm.items.ModItems;
import com.hbm.particle.ParticleContrail;
import com.hbm.render.entity.rocket.RenderMissileNuclear;
import com.hbm.render.item.ItemRenderMissile;
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
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileBuster;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileCluster;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileEmp;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileH;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileHE;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileNuclear;
import com.wartec.wartecmod.render.entity.missile.RenderHypersonicCruiseMissileNuclear;
import com.wartec.wartecmod.render.entity.missile.RenderCruiseMissileFAE;
import com.wartec.wartecmod.render.entity.missile.RenderHypersonicCruiseMissileHE;
import com.wartec.wartecmod.render.entity.missile.RenderIskanderMissile;
import com.wartec.wartecmod.render.entity.missile.RenderKalibrMissile;
import com.wartec.wartecmod.render.entity.missile.RenderLrhwMissile;
import com.wartec.wartecmod.render.entity.missile.RenderMissileAntiAirTier1;
import com.wartec.wartecmod.render.entity.missile.RenderMissileAntiBallisticNuclear;
import com.wartec.wartecmod.render.entity.missile.RenderMissileMicroGas;
import com.wartec.wartecmod.render.entity.missile.RenderMissileMicroNeutron;
import com.wartec.wartecmod.render.entity.missile.RenderMissileSlbm;
import com.wartec.wartecmod.render.entity.missile.RenderSatelliteMissileNuclear;
import com.wartec.wartecmod.render.entity.missile.RenderSupersonicCruiseMissileHE;
import com.wartec.wartecmod.render.entity.missile.RenderSupersonicCruiseMissileH;
import com.wartec.wartecmod.render.entity.missile.RenderTomahawkMissile;
import com.wartec.wartecmod.render.item.ItemRenderCj10Missile;
import com.wartec.wartecmod.render.item.ItemRenderIskanderMissile;
import com.wartec.wartecmod.render.item.ItemRenderKalibrMissile;
import com.wartec.wartecmod.render.item.ItemRenderTomahawkMissile;
import com.wartec.wartecmod.render.tileentity.RenderTileEntityBallisticMissileLauncher;
import com.wartec.wartecmod.render.tileentity.RenderTileEntityDecoBlock;
import com.wartec.wartecmod.render.tileentity.RenderTileEntityLaunchTube;
import com.wartec.wartecmod.tileentity.deco.TileEntityDecoBlock;
import com.wartec.wartecmod.tileentity.launcher.TileEntityBallisticMissileLauncher;
import com.wartec.wartecmod.tileentity.launcher.TileEntityLaunchTube;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class wartecmodClientProxy extends wartecmodProxy {
	
	@Override
	public void registerRenderers() {
		//TE
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecoBlock.class, new RenderTileEntityDecoBlock());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchTube.class, new RenderTileEntityLaunchTube());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBallisticMissileLauncher.class, new RenderTileEntityBallisticMissileLauncher());
         //Entities
    	RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileHE.class, new RenderCruiseMissileHE());
    	RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileH.class, new RenderCruiseMissileH());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissileSlbm.class, new RenderMissileSlbm());
        RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileNuclear.class, new RenderCruiseMissileNuclear());
        RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileTB.class, new RenderCruiseMissileFAE());
        RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileBuster.class, new RenderCruiseMissileBuster());
        RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileEmp.class, new RenderCruiseMissileEmp());
        RenderingRegistry.registerEntityRenderingHandler(EntityCruiseMissileCluster.class, new RenderCruiseMissileCluster());
        RenderingRegistry.registerEntityRenderingHandler(EntityHypersonicCruiseMissileHE.class, new RenderHypersonicCruiseMissileHE());
        RenderingRegistry.registerEntityRenderingHandler(EntityIskanderMissile.class, new RenderIskanderMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntityLrhwMissile.class, new RenderLrhwMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntitySupersonicCruiseMissileHE.class, new RenderSupersonicCruiseMissileHE());
        RenderingRegistry.registerEntityRenderingHandler(EntityTomahawkMissile.class, new RenderTomahawkMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntityKalibrMissile.class, new RenderKalibrMissile());
        RenderingRegistry.registerEntityRenderingHandler(EntitySupersonicCruiseMissileH.class, new RenderSupersonicCruiseMissileH());
        RenderingRegistry.registerEntityRenderingHandler(EntityHypersonicCruiseMissileNuclear.class, new RenderHypersonicCruiseMissileNuclear());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissileMicroGas.class, new RenderMissileMicroGas());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissileMicroNeutron.class, new RenderMissileMicroNeutron());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissileAntiBallisticNuclear.class, new RenderMissileAntiBallisticNuclear());
        RenderingRegistry.registerEntityRenderingHandler(EntityMissileAntiAirTier1.class, new RenderMissileAntiAirTier1());
        RenderingRegistry.registerEntityRenderingHandler(EntitySatelliteMissileNuclear.class, new RenderSatelliteMissileNuclear());
        
         //Items
		MinecraftForgeClient.registerItemRenderer(wartecmodItems.itemKalibrMissile, new ItemRenderKalibrMissile());
		MinecraftForgeClient.registerItemRenderer(wartecmodItems.itemTomahawkMissile, new ItemRenderTomahawkMissile());
		MinecraftForgeClient.registerItemRenderer(wartecmodItems.itemCj10Missile, new ItemRenderCj10Missile());
		MinecraftForgeClient.registerItemRenderer(wartecmodItems.itemIskanderMissile, new ItemRenderIskanderMissile());
		
		
	}
}
