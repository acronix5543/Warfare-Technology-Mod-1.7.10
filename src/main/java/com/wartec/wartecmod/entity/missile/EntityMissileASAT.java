package com.wartec.wartecmod.entity.missile;

import com.hbm.explosion.ExplosionLarge;
import com.hbm.packet.AuxParticlePacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.saveddata.SatelliteSavedData;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMissileASAT extends Entity {

    private final int satId;

    double acceleration = 0.00D;

    public EntityMissileASAT(World world, float x, float y, float z, int satId) {
        super(world);
        this.ignoreFrustumCheck = true;
        this.setLocationAndAngles(x, y, z, 0, 0);
        this.satId = satId;
    }

    @Override
    protected void entityInit() { }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) { }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) { }

    @Override
    public void onUpdate() {

        if(motionY < 3.0D) {
            acceleration += 0.0008D;
            motionY += acceleration;
        }

        this.lastTickPosX = this.prevPosX = posX;
        this.lastTickPosY = this.prevPosY = posY;
        this.lastTickPosZ = this.prevPosZ = posZ;
        this.setLocationAndAngles(posX + this.motionX, posY + this.motionY, posZ + this.motionZ, 0, 0);

        PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacket(posX, posY, posZ, 2),
                new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, posX, posY, posZ, 300));

        if(!worldObj.isRemote) {
            if (this.posY >= 600) {
                System.out.println("deleted sat "+satId);

                SatelliteSavedData sd = SatelliteSavedData.getData(worldObj);
                sd.sats.remove(satId);
                sd.markDirty();

                this.setDead();
            }
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