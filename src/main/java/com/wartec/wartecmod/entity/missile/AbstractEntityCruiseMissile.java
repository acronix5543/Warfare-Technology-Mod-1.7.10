package com.wartec.wartecmod.entity.missile;

import api.hbm.entity.IRadarDetectable;
import com.hbm.entity.logic.IChunkLoader;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.main.MainRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
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
    double range;
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

        range = (Math.sqrt(((targetX - startX) * (targetX - startX)) + ((targetZ - startZ) * (targetZ - startZ))));

        this.motionY = 0.25;

        velocity = 1;

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

        MainRegistry.proxy.effectNT(data);
    }



    @Override
    public void onUpdate() {

        this.dataWatcher.updateObject(8, this.health);

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;

        if(ticksExisted < 40) { // extraction from vls cell
            velocity = 0.7;
            this.rotationPitch = 90;
        } else if(ticksExisted < 160) { // from launch to booster seperation
            if(velocity < 2.8) {
                velocity += 0.01;
            }
            this.rotationPitch -= 0.5;
        } else if(ticksExisted < 200) { // to top point
            this.rotationPitch -= 0.7;
            this.velocity -= 0.01;
        } else if(ticksExisted < 300) { // to cruise alt : rot down
            this.velocity += 0.06;
            this.rotationPitch -= 0.4;
        }
        else if(ticksExisted < 400) { // to cruise alt : rot up
            this.velocity += 0.07;
            this.rotationPitch += 0.4;
            System.out.println("to cruise alt down pitch: "+rotationPitch);
        } else if(this.getDistance(targetX, targetY, targetZ) < 60) { // final flight up
            System.out.println("final flight up");
        } else if(this.getDistance(targetX, targetY, targetZ) < 20) { // flight down
            System.out.println("final flight down");
        } else { // cruise
            System.out.println("Cruise velo "+velocity);
        }

        Vec3 vector = Vec3.createVectorHelper(1, 0 , 0);
        vector.xCoord *= velocity;
        vector.zCoord *= velocity;

        vector.rotateAroundY(rotationYaw);


        if(motionY > 0) {
            motionX += vector.xCoord;
            motionZ += vector.zCoord;
        }

        if(motionY < 0) {
            motionX -= vector.xCoord;
            motionZ -= vector.zCoord;
        }

        this.setLocationAndAngles(posX + this.motionX * velocity, posY + this.motionY * velocity, posZ + this.motionZ * velocity, 0, 0);

        if (    this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.air &&
                this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.water &&
                this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.flowing_water
        ) {
            if(!this.worldObj.isRemote)
            {
                onImpact();
            }
            this.setDead();
            return;
        }

        loadNeighboringChunks((int)(posX / 16), (int)(posZ / 16));

        spawnExhaust(posX, posY, posZ);

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

    List<ChunkCoordIntPair> loadedChunks = new ArrayList<ChunkCoordIntPair>();

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


    private void MissileToCruiseMissile() {
        ExplosionLarge.spawnParticles(worldObj, posX, posY, posZ, 7);
        this.dataWatcher.updateObject(9, 2);
    }

    @Override
    public RadarTargetType getTargetType() {
        return RadarTargetType.MISSILE_TIER3;
    }
}