package com.wartec.wartecmod.entity.missile;

import com.hbm.items.ModItems;
import com.wartec.wartecmod.entity.logic.ExplosionLargeAdvanced;
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.tileentity.vls.TileEntityVlsExhaust;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityCruiseMissileHE extends EntitySubsonicCruiseMissileBase {

	public EntityCruiseMissileHE(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	public EntityCruiseMissileHE(World world, float x, float y, float z, int a, int b, TileEntityVlsExhaust exh) {
		super(world, x, y, z, a, b, exh);
		this.isSubsonic = true;
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