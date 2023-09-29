package com.wartec.wartecmod.entity.missile;

import api.hbm.entity.IRadarDetectable;
import com.hbm.entity.logic.IChunkLoader;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.main.MainRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import java.util.ArrayList;
import java.util.List;

public class AbstractEntityCruiseMissile extends Entity implements IChunkLoader, IRadarDetectable {

    int startX;
    int startY;
    int startZ;
    int targetX;
    int targetY;
    int targetZ;
    double velocity;
    private ForgeChunkManager.Ticket loaderTicket;
    public int health = 10;


    public AbstractEntityCruiseMissile(World p_i1582_1_) {
        super(p_i1582_1_);
        this.ignoreFrustumCheck = true;
        startX = (int) posX;
        startY = (int) posY;
        startZ = (int) posZ;
        targetX = (int) posX;
        targetZ = (int) posZ;
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }

    public boolean attackEntityFrom(DamageSource src, float dmg)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
                health -= dmg;

                if (this.health <= 0)
                {
                    this.setDead();
                    this.killMissile();
                }
            }

            return true;
        }
    }


    private void killMissile() {
        ExplosionLarge.explode(worldObj, posX, posY, posZ, 5, true, false, true);
        ExplosionLarge.spawnShrapnelShower(worldObj, posX, posY, posZ, motionX, motionY, motionZ, 15, 0.075);
        ExplosionLarge.spawnMissileDebris(worldObj, posX, posY, posZ, motionX, motionY, motionZ, 0.25, getDebris(), getDebrisRareDrop());
    }

    public AbstractEntityCruiseMissile(World world, float x, float y, float z, int a, int b) {
        super(world);
        this.ignoreFrustumCheck = true;
        this.setLocationAndAngles(x, y, z, 0, 0);
        startX = (int) x;
        startZ = (int) z;
        targetX = a;
        targetZ = b;

        this.motionY = 0.25;

        velocity = 1;

        rotationPitch = 90;
        double tx = (double) (targetX - startX);
        double tz = (double) (targetZ - startZ);
        double angle = Math.atan2(tx, tz);
        
        rotationYaw = (float) MathHelper.wrapAngleTo180_double(angle * (180/Math.PI) + 180);
        prevRotationYaw = rotationYaw;
        System.out.println(rotationYaw);
        world.updateEntity(this);

        this.setSize(1.5F, 1.5F);
    }

    @Override
    protected void entityInit() {
        init(ForgeChunkManager.requestTicket(MainRegistry.instance, worldObj, ForgeChunkManager.Type.ENTITY));
        this.dataWatcher.addObject(8, this.health);
        this.dataWatcher.addObject(9, 1);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
        motionX = nbt.getDouble("moX");
        motionY = nbt.getDouble("moY");
        motionZ = nbt.getDouble("moZ");
        posX = nbt.getDouble("poX");
        posY = nbt.getDouble("poY");
        posZ = nbt.getDouble("poZ");
        targetX = nbt.getInteger("tX");
        targetY = nbt.getInteger("tY");
        targetZ = nbt.getInteger("tZ");
        startX = nbt.getInteger("sX");
        startY = nbt.getInteger("sY");
        startZ = nbt.getInteger("sZ");
        velocity = nbt.getDouble("veloc");

        rotationYaw = nbt.getFloat("rotYaw");
        rotationPitch = nbt.getFloat("rotPitch");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setDouble("moX", motionX);
        nbt.setDouble("moY", motionY);
        nbt.setDouble("moZ", motionZ);
        nbt.setDouble("poX", posX);
        nbt.setDouble("poY", posY);
        nbt.setDouble("poZ", posZ);
        nbt.setInteger("tX", targetX);
        nbt.setInteger("tY", targetY);
        nbt.setInteger("tZ", targetZ);
        nbt.setInteger("sX", startX);
        nbt.setInteger("sY", startY);
        nbt.setInteger("sZ", startZ);
        nbt.setDouble("veloc", velocity);

        nbt.setFloat("rotYaw", rotationYaw);
        nbt.setFloat("rotPitch", rotationPitch);
    }

    private void spawnExhaust(double x, double y, double z) {

        NBTTagCompound data = new NBTTagCompound();
        data.setString("type", "exhaust");
        data.setString("mode", "soyuz");
        data.setInteger("count", 5);
        data.setDouble("width", worldObj.rand.nextDouble() * 0.25 - 0.5);
        data.setDouble("posX", x);
        data.setDouble("posY", y);
        data.setDouble("posZ", z);

        try {
            MainRegistry.proxy.effectNT(data);
        } catch (Exception ignored) {}
    }

    /**
     * interpolated look vector
     */
    public Vec3 getLook(float p_70676_1_)
    {
        float f1;
        float f2;
        float f3;
        float f4;

        if (p_70676_1_ == 1.0F)
        {
            f1 = MathHelper.cos(-this.rotationYaw * 0.017453292F - (float)Math.PI);
            f2 = MathHelper.sin(-this.rotationYaw * 0.017453292F - (float)Math.PI);
            f3 = -MathHelper.cos(-this.rotationPitch * 0.017453292F);
            f4 = MathHelper.sin(-this.rotationPitch * 0.017453292F);
            return Vec3.createVectorHelper((double)(f2 * f3), (double)f4, (double)(f1 * f3));
        }
        else
        {
            f1 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * p_70676_1_;
            f2 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * p_70676_1_;
            f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
            f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
            float f5 = -MathHelper.cos(-f1 * 0.017453292F);
            float f6 = MathHelper.sin(-f1 * 0.017453292F);
            return Vec3.createVectorHelper(f4 * f5, f6, f3 * f5);
        }
    }



    @Override
    public void onUpdate() {

        this.dataWatcher.updateObject(8, this.health);

        if (!worldObj.isRemote) {

            final double distanceToTarget = getDistance(targetX, targetY, targetZ);

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;

            if(ticksExisted < 110) {
                this.dataWatcher.updateObject(9, 1);
            } else {
                this.dataWatcher.updateObject(9, 2);
            }

            if (ticksExisted < 40) { // extraction from vls cell
                velocity = 0.3;
                this.rotationPitch = 90;
            } else if (ticksExisted < 160 - 50) { // from launch to booster seperation
                if (velocity < 2.8) {
                    velocity += 0.01;
                }
                this.rotationPitch -= 0.9; // 0.5 old
            } else if (ticksExisted < 200 - 30) { // to top point
                this.rotationPitch -= 1.2; // 0.7 old
                this.velocity -= 0.01;
            } else if (ticksExisted < 180) { // to cruise alt : rot down
                this.velocity += 0.16;
                this.rotationPitch -= 0.045; // 0.4 old
            } else if (ticksExisted < 400 - 40) { // to cruise alt : rot up
                if(rotationPitch < 0) {
                    this.velocity += 0.17;
                    this.rotationPitch += 1.2; // 0.4 old
                } if(rotationPitch > 0) {
                    this.velocity += 0.17;
                    this.rotationPitch -= 0.2;
                }
            } else { // cruise
                if (velocity < 10) { // 243 m/s = 879 km/h = tomahawk speed
                    velocity += 2;
                }
                System.out.println("Cruise velo " + velocity);
            }

            if (distanceToTarget < 60 && distanceToTarget > 20) { // final flight up
                System.out.println("final flight up");
            } else if (distanceToTarget < 20) { // flight down
                System.out.println("final flight down");
            }

            if(ticksExisted > 110 && ticksExisted < 115) {
                ExplosionLarge.spawnParticles(worldObj, posX, posY, posZ, 2);
            }

            Vec3 look = getLook((float) velocity);

            motionX = look.xCoord;
            motionY = -look.yCoord;
            motionZ = look.zCoord;

            this.lastTickPosX = this.prevPosX = this.posX = (posX + this.motionX * velocity);
            this.lastTickPosY = this.prevPosY = this.posY = (posY + this.motionY * velocity) + (double) this.yOffset;
            this.lastTickPosZ = this.prevPosZ = this.posZ = (posZ + this.motionZ * velocity);
            this.setPosition(this.posX, this.posY, this.posZ);

            if (this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != Blocks.air &&
                    this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != Blocks.water &&
                    this.worldObj.getBlock((int) this.posX, (int) this.posY, (int) this.posZ) != Blocks.flowing_water
            ) {
                if (!this.worldObj.isRemote) {
                    onImpact();
                }
                this.setDead();
                return;
            }

            if(this.getDistance(startX, startY, startZ) > 440000) { // range (real tomahawk)
                this.setDead();
                return;
            }

            loadNeighboringChunks((int) (posX / 16), (int) (posZ / 16));
        }

        if (ticksExisted < 160 - 50) {
            spawnExhaust(posX, posY, posZ);
        }

    }





    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        return distance < 500000;
    }

    public void onImpact() {

    }
    public List<ItemStack> getDebris() {
        return new ArrayList<>();
    }
    public ItemStack getDebrisRareDrop() {
        return null;
    }

    // todo: bring back

   // public abstract void onImpact();
//
   // public abstract List<ItemStack> getDebris();
//
   // public abstract ItemStack getDebrisRareDrop();

    public void init(ForgeChunkManager.Ticket ticket) {
        if(!worldObj.isRemote) {

            if(ticket != null) {

                if(loaderTicket == null) {

                    loaderTicket = ticket;
                    loaderTicket.bindEntity(this);
                    loaderTicket.getModData();
                }

                ForgeChunkManager.forceChunk(loaderTicket, new ChunkCoordIntPair(chunkCoordX, chunkCoordZ));
            }
        }
    }

    List<ChunkCoordIntPair> loadedChunks = new ArrayList<>();

    public void loadNeighboringChunks(int newChunkX, int newChunkZ)
    {
        if(!worldObj.isRemote && loaderTicket != null)
        {
            for(ChunkCoordIntPair chunk : loadedChunks)
            {
                ForgeChunkManager.unforceChunk(loaderTicket, chunk);
            }

            loadedChunks.clear();
            loadedChunks.add(new ChunkCoordIntPair(newChunkX, newChunkZ));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX + 1, newChunkZ + 1));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX - 1, newChunkZ - 1));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX + 1, newChunkZ - 1));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX - 1, newChunkZ + 1));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX + 1, newChunkZ));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX, newChunkZ + 1));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX - 1, newChunkZ));
            loadedChunks.add(new ChunkCoordIntPair(newChunkX, newChunkZ - 1));

            for(ChunkCoordIntPair chunk : loadedChunks)
            {
                ForgeChunkManager.forceChunk(loaderTicket, chunk);
            }
        }
    }


    @Override
    public RadarTargetType getTargetType() {
        return RadarTargetType.MISSILE_TIER3;
    }
}
