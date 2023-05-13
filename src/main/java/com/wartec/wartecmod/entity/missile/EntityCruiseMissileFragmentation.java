package com.wartec.wartecmod.entity.missile;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.standard.BlockAllocatorStandard;
import com.hbm.explosion.vanillant.standard.BlockProcessorStandard;
import com.hbm.explosion.vanillant.standard.EntityProcessorCross;
import com.hbm.explosion.vanillant.standard.ExplosionEffectStandard;
import com.hbm.explosion.vanillant.standard.PlayerProcessorStandard;
import com.hbm.items.ModItems;
import com.wartec.wartecmod.entity.logic.ExplosionLargeAdvanced;
import com.wartec.wartecmod.items.wartecmodItems;

import api.hbm.entity.IRadarDetectable.RadarTargetType;
import com.wartec.wartecmod.tileentity.vls.TileEntityVlsExhaust;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCruiseMissileFragmentation extends EntitySubsonicCruiseMissileBase {

	public EntityCruiseMissileFragmentation(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	public EntityCruiseMissileFragmentation(World world, float x, float y, float z, int a, int b, TileEntityVlsExhaust exhaust) {
		super(world, x, y, z, a, b, exhaust);
		this.isSubsonic = true;
	}
	

	@Override
	public void onImpact() {
		//ExplosionLarge.explode(worldObj, posX, posY, posZ, 20.0F, true, true, true);
		ExplosionLargeAdvanced explosionLargeAdvanced = new ExplosionLargeAdvanced();
		explosionLargeAdvanced.ExplosionAdvanced(worldObj, posX, posY, posZ, 20F, 2F, false);
	}

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(ModItems.plate_steel, 10));
		list.add(new ItemStack(ModItems.plate_titanium, 6));
		list.add(new ItemStack(ModItems.thruster_medium, 1));
		list.add(new ItemStack(ModItems.circuit_targeting_tier2, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return new ItemStack(wartecmodItems.itemWarheadHeCM);
	}
	
	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER0;
	}

	
}