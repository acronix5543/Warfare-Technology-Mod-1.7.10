package com.wartec.wartecmod.entity.projectile;

import com.hbm.config.WorldConfig;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacket;
import com.hbm.packet.PacketDispatcher;
import com.wartec.wartecmod.entity.logic.ExplosionLargeAdvanced;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySatelliteRods extends Entity{

	public EntitySatelliteRods(World p_i1582_1_) {
		super(p_i1582_1_);
		this.ignoreFrustumCheck = true;
	}

	@Override
	protected void entityInit() { }

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) { }

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) { }
	
	@Override
	public void onUpdate() {
		
		this.lastTickPosX = this.prevPosX = posX;
		this.lastTickPosY = this.prevPosY = posY;
		this.lastTickPosZ = this.prevPosZ = posZ;
		this.setPosition(posX + this.motionX, posY + this.motionY, posZ + this.motionZ);

		//worldObj.playSoundEffect(posX, posY, posZ, "hbm:weapon.missileTakeOff", 10000, 1.0F);

		motionY = -10;

		if(this.worldObj.isRemote) {

			NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "exhaust");
			data.setString("mode", "meteor");
			data.setInteger("count", 10);
			data.setDouble("width", 1);
			data.setDouble("posX", posX - motionX);
			data.setDouble("posY", posY - motionY);
			data.setDouble("posZ", posZ - motionZ);

			MainRegistry.proxy.effectNT(data);
		}
		new TargetPoint(worldObj.provider.dimensionId, posX, posY, posZ, 300);

		if(this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != Blocks.air) {
			if(!this.worldObj.isRemote) {

				ExplosionLargeAdvanced explosionLargeAdvanced = new ExplosionLargeAdvanced();
				explosionLargeAdvanced.ThermobaricExplosion(worldObj, posX, posY, posZ, 75F, 15F, true);
				ExplosionLarge.spawnShrapnels(worldObj, posX, posY, posZ, 55);
				ExplosionLargeAdvanced.standardMush(worldObj, posX, posY, posZ, 60);
			}

			this.setDead();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float p_70070_1_) {
		return 15728880;
	}

	@Override
	public float getBrightness(float p_70013_1_) {
		return 1.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		return distance < 25000;
	}
}