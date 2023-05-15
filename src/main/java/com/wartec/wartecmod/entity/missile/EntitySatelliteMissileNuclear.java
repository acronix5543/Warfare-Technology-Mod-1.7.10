package com.wartec.wartecmod.entity.missile;

import api.hbm.entity.ITurretTargetable;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityCloudTom;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.entity.logic.EntityTomBlast;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.packet.AuxParticlePacket;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySatelliteMissileNuclear extends Entity implements ITurretTargetable {

	public EntitySatelliteMissileNuclear(World p_i1582_1_) {
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

		motionY = -2;
		
		PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacket(posX, posY+8, posZ, 2),
				new TargetPoint(worldObj.provider.dimensionId, posX, posY, posZ, 300));

		if(this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != Blocks.air) {
			if(!this.worldObj.isRemote) {
				worldObj.spawnEntityInWorld(EntityNukeExplosionMK5.statFac(worldObj, 200, posX, posY, posZ));

				EntityNukeCloudSmall entity2 = new EntityNukeCloudSmall(this.worldObj, 1000, 200 * 0.005F);
				entity2.posX = this.posX;
				entity2.posY = this.posY;
				entity2.posZ = this.posZ;
				this.worldObj.spawnEntityInWorld(entity2);	
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