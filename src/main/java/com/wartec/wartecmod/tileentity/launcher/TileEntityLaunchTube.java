/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.hbm.lib.Library
 *  com.hbm.packet.AuxElectricityPacket
 *  com.hbm.packet.PacketDispatcher
 *  com.hbm.tileentity.TileEntityLoadedBase
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.wartec.wartecmod.tileentity.launcher;

import api.hbm.energy.IEnergyUser;
import api.hbm.item.IDesignatorItem;
import com.hbm.config.GeneralConfig;
import com.hbm.entity.missile.EntityMissileAntiBallistic;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.Spaghetti;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.NBTPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;
import com.wartec.wartecmod.entity.missile.EntityMissileAntiAirTier1;
import com.wartec.wartecmod.entity.missile.EntityMissileAntiBallisticNuclear;
import com.wartec.wartecmod.items.IMissileSpawningItem;
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.packet.PacketRegistry;
import com.wartec.wartecmod.packet.TELaunchTubePacket;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Constructor;

public class TileEntityLaunchTube
		extends TileEntityLoadedBase
		implements ISidedInventory,
		IBomb,
		IEnergyUser {
	public ItemStack[] slots = new ItemStack[3];
	public long power;
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{0, 1, 2};
	private static final int[] slots_side = new int[]{0};
	public int state = 0;

	public int openingAnimation = 0;
	public boolean open = false;
	public int shoot = 0;

	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.slots[i] != null) {
			ItemStack itemStack = this.slots[i];
			this.slots[i] = null;
			return itemStack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		this.slots[i] = itemStack;
		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "container.LaunchTube";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
			return false;
		}
		return player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			if (this.slots[i].stackSize <= j) {
				ItemStack itemStack = this.slots[i];
				this.slots[i] = null;
				return itemStack;
			}
			ItemStack itemStack1 = this.slots[i].splitStack(j);
			if (this.slots[i].stackSize == 0) {
				this.slots[i] = null;
			}
			return itemStack1;
		}
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("items", 10);
		this.power = nbt.getLong("power");

		openingAnimation = nbt.getInteger("openanim");
		shoot = nbt.getInteger("shoot");
		open = nbt.getBoolean("open");

		this.slots = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound nbt1 = list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("slot");
			if (b0 < 0 || b0 >= this.slots.length) continue;
			this.slots[b0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		nbt.setLong("power", this.power);

		nbt.setInteger("openanim",openingAnimation);
		nbt.setInteger("shoot",shoot);
		nbt.setBoolean("open",open);

		for (int i = 0; i < this.slots.length; ++i) {
			if (this.slots[i] == null) continue;
			NBTTagCompound nbt1 = new NBTTagCompound();
			nbt1.setByte("slot", (byte)i);
			this.slots[i].writeToNBT(nbt1);
			list.appendTag((NBTBase)nbt1);
		}
		nbt.setTag("items", (NBTBase)list);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return p_94128_1_ == 0 ? slots_bottom : (p_94128_1_ == 1 ? slots_top : slots_side);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStack, int j) {
		return this.isItemValidForSlot(i, itemStack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		return true;
	}

	public long getPowerScaled(long i) {
		return this.power * i / 100000L;
	}

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if(open && openingAnimation < 90)
				openingAnimation += 3;
			if( !open && openingAnimation > 0)
				openingAnimation -= 3;

			if(shoot == 50)
				open = true;
			if(openingAnimation >= 86 && shoot == 50) {
				this.explode(this.worldObj, xCoord, yCoord, zCoord);
				shoot--;
			}
			if(shoot > 0 && shoot < 50)
				shoot--;
			if(shoot == 0)
				open = false;

			this.power = Library.chargeTEFromItems(this.slots, 2, this.power, 100000L);
			this.updateConnections();
			PacketRegistry.wrapper.sendToAllAround(new TELaunchTubePacket(this.xCoord, this.yCoord, this.zCoord, this.slots[0], openingAnimation), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 250.0));
			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(this.xCoord, this.yCoord, this.zCoord, this.power), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 50.0));
		}
	}

	private void updateConnections() {
		this.trySubscribe(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord, Library.POS_X);
		this.trySubscribe(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord, Library.NEG_X);
		this.trySubscribe(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1, Library.POS_Z);
		this.trySubscribe(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1, Library.NEG_Z);
		this.trySubscribe(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord, Library.NEG_Y);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	public void setPower(long i) {
		this.power = i;
	}

	@Override
	public long getPower() {
		return this.power;
	}

	@Override
	public long getMaxPower() {
		return 100000L;
	}

	@Override
	public long transferPower(long power) {
		this.power += power;
		if (this.power > this.getMaxPower()) {
			long overshoot = this.power - this.getMaxPower();
			this.power = this.getMaxPower();
			return overshoot;
		}
		return 0L;
	}

	@Override
	public boolean canConnect(ForgeDirection dir) {
		return dir != ForgeDirection.UP && dir != ForgeDirection.UNKNOWN;
	}

	@SideOnly(value=Side.CLIENT)
	@Override
	public double getMaxRenderDistanceSquared() {
		return 65536.0;
	}

	@Spaghetti(value="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA *takes breath* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
	public IBomb.BombReturnCode explode(World world, int x, int y, int z) {
		TileEntityLaunchTube entity = (TileEntityLaunchTube)world.getTileEntity(x, y, z);
		if (entity.slots[0] == null || world.isRemote) {
			return IBomb.BombReturnCode.ERROR_MISSING_COMPONENT;
		}
		if (entity.slots[1] != null && entity.slots[1].getItem() instanceof IDesignatorItem && entity.power >= 75000L) {
			if (!((IDesignatorItem)entity.slots[1].getItem()).isReady(world, entity.slots[1], x, y, z)) {
				return IBomb.BombReturnCode.ERROR_MISSING_COMPONENT;
			}
			int xCoord = entity.slots[1].stackTagCompound.getInteger("xCoord");
			int zCoord = entity.slots[1].stackTagCompound.getInteger("zCoord");
			if (xCoord == entity.xCoord && zCoord == entity.zCoord) {
				xCoord += 6;
			}
			if(entity.slots[0].getItem() instanceof IMissileSpawningItem) {
				Class<? extends Entity> missile = ((IMissileSpawningItem) entity.slots[0].getItem()).getMissile();
				Entity missileEntity;
				try {
					Constructor<?> constructor = missile.getConstructor(World.class, float.class, float.class, float.class, int.class, int.class);
					missileEntity = (Entity) constructor.newInstance(world, (float)x + 0.5f, (float)y + 11.0f, (float)z + 0.5f, xCoord, zCoord);

					world.spawnEntityInWorld(missileEntity);
					world.playSoundEffect((double)x, (double)(y + 10), (double)z, "wartecmod:weapon.CruiseMissileTakeoff", 10.0f, 1.0f);
					ExplosionLarge.spawnParticles((World)world, (double)x, (double)((float)y + 11.0f), (double)z, (int)5);
					entity.power -= 50000L;
					entity.slots[0] = null;
					if (GeneralConfig.enableExtendedLogging) {
						MainRegistry.logger.log(Level.INFO, "[MISSILE] Tried to launch missile at " + x + " / " + y + " / " + z + " to " + xCoord + " / " + zCoord + "!");
					}
					return IBomb.BombReturnCode.LAUNCHED;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		if (entity.slots[0] != null && entity.slots[0].getItem() == ModItems.missile_anti_ballistic && entity.power >= 75000L) {
			EntityMissileAntiBallistic missile = new EntityMissileAntiBallistic(world);
			missile.posX = (float)x + 0.5f;
			missile.posY = (float)y + 11;
			missile.posZ = (float)z + 0.5f;
			world.spawnEntityInWorld((Entity)missile);
			entity.power -= 75000L;
			entity.slots[0] = null;
			world.playSoundEffect((double)x, (double)y, (double)z, "hbm:weapon.missileTakeOff", 2.0f, 1.0f);
			return IBomb.BombReturnCode.LAUNCHED;
		}
		if (entity.slots[0] != null && entity.slots[0].getItem() == wartecmodItems.itemMissileAntiBallisticNuclear && entity.power >= 5000L) {
			EntityMissileAntiBallisticNuclear missile = new EntityMissileAntiBallisticNuclear(world);
			missile.posX = (float)x + 0.5f;
			missile.posY = (float)y + 11;
			missile.posZ = (float)z + 0.5f;
			world.spawnEntityInWorld((Entity)missile);
			entity.power -= 5000L;
			entity.slots[0] = null;
			world.playSoundEffect((double)x, (double)y, (double)z, "hbm:weapon.missileTakeOff", 2.0f, 1.0f);
			return IBomb.BombReturnCode.LAUNCHED;
		}
		if (entity.slots[0] != null && entity.slots[0].getItem() == wartecmodItems.itemMissileAntiAirTier1 && entity.power >= 50000L) {
			EntityMissileAntiAirTier1 missile = new EntityMissileAntiAirTier1(world);
			missile.posX = (float)x + 0.5f;
			missile.posY = (float)y + 11;
			missile.posZ = (float)z + 0.5f;
			world.spawnEntityInWorld((Entity)missile);
			entity.power -= 5000L;
			entity.slots[0] = null;
			world.playSoundEffect((double)x, (double)y, (double)z, "hbm:weapon.missileTakeOff", 2.0f, 1.0f);
			return IBomb.BombReturnCode.LAUNCHED;
		}
		return IBomb.BombReturnCode.ERROR_MISSING_COMPONENT;
	}
}

