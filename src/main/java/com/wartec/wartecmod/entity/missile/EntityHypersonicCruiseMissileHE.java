package com.wartec.wartecmod.entity.missile;

import java.util.ArrayList;
import java.util.List;

import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;
import com.wartec.wartecmod.entity.logic.ExplosionLargeAdvanced;
import com.wartec.wartecmod.items.wartecmodItems;

import api.hbm.entity.IRadarDetectable.RadarTargetType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityHypersonicCruiseMissileHE extends EntityHypersonicCruiseMissileBase {
	
	public EntityHypersonicCruiseMissileHE(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	public EntityHypersonicCruiseMissileHE(World world, float x, float y, float z, int a, int b) {
		super(world, x, y, z, a, b);
		this.isHypersonic = true;
	}

	@Override
	public void onImpact() {
		ExplosionLargeAdvanced explosionLargeAdvanced = new ExplosionLargeAdvanced();
		explosionLargeAdvanced.ExplosionAdvanced(worldObj, posX, posY, posZ, 20F, 2F, true);
		
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


