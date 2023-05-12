package com.wartec.wartecmod.entity.missile;

import java.util.ArrayList;
import java.util.List;

import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.missile.EntityMissileBaseAdvanced;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.items.ModItems;
import com.wartec.wartecmod.items.wartecmodItems;

import api.hbm.entity.IRadarDetectable.RadarTargetType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileMicroGas extends EntityBallisticMissileBase {

	public EntityMissileMicroGas(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	public EntityMissileMicroGas(World world, float x, float y, float z, int a, int b) {
		super(world, x, y, z, a, b);
	}

	@Override
	public void onImpact() {
	this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), 0);
	ExplosionChaos.spawnChlorine(worldObj, posX - motionX, posY - motionY, posZ - motionZ, 750, 2.5, 0);
	}

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(ModItems.plate_titanium, 10));
		list.add(new ItemStack(ModItems.plate_steel, 12));
		list.add(new ItemStack(ModItems.plate_aluminium, 8));
		list.add(new ItemStack(ModItems.thruster_large, 1));
		list.add(new ItemStack(wartecmodItems.itemGuidanceSystemTier5, 1));
		list.add(new ItemStack(ModItems.circuit_targeting_tier4, 1));
		
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return new ItemStack(wartecmodItems.itemHWarhead);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER0;
	}
}