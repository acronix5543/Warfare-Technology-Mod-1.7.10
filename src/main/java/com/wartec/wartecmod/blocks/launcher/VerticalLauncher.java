package com.wartec.wartecmod.blocks.launcher;

import java.util.Random;

import org.apache.logging.log4j.Level;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.bomb.LaunchPad;
import com.hbm.config.GeneralConfig;
import com.hbm.entity.missile.EntityCarrier;
import com.hbm.entity.missile.EntityMissileAntiBallistic;
import com.hbm.entity.missile.EntityMissileBHole;
import com.hbm.entity.missile.EntityMissileBunkerBuster;
import com.hbm.entity.missile.EntityMissileBurst;
import com.hbm.entity.missile.EntityMissileBusterStrong;
import com.hbm.entity.missile.EntityMissileCluster;
import com.hbm.entity.missile.EntityMissileClusterStrong;
import com.hbm.entity.missile.EntityMissileDoomsday;
import com.hbm.entity.missile.EntityMissileDrill;
import com.hbm.entity.missile.EntityMissileEMP;
import com.hbm.entity.missile.EntityMissileEMPStrong;
import com.hbm.entity.missile.EntityMissileEndo;
import com.hbm.entity.missile.EntityMissileExo;
import com.hbm.entity.missile.EntityMissileGeneric;
import com.hbm.entity.missile.EntityMissileIncendiary;
import com.hbm.entity.missile.EntityMissileIncendiaryStrong;
import com.hbm.entity.missile.EntityMissileInferno;
import com.hbm.entity.missile.EntityMissileMicro;
import com.hbm.entity.missile.EntityMissileMirv;
import com.hbm.entity.missile.EntityMissileNuclear;
import com.hbm.entity.missile.EntityMissileRain;
import com.hbm.entity.missile.EntityMissileSchrabidium;
import com.hbm.entity.missile.EntityMissileShuttle;
import com.hbm.entity.missile.EntityMissileStrong;
import com.hbm.entity.missile.EntityMissileTaint;
import com.hbm.entity.missile.EntityMissileVolcano;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.Spaghetti;
import com.hbm.interfaces.IBomb.BombReturnCode;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityLaunchPad;
import com.hbm.util.ParticleUtil;
import com.wartec.wartecmod.blocks.wartecmodBlocks;
import com.wartec.wartecmod.entity.missile.EntityCJ10Missile;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileBuster;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileCluster;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileEmp;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileH;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileHE;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileNuclear;
import com.wartec.wartecmod.entity.missile.EntityHypersonicCruiseMissileNuclear;
import com.wartec.wartecmod.entity.missile.EntityHypersonicCruiseMissileHE;
import com.wartec.wartecmod.entity.missile.EntityKalibrMissile;
import com.wartec.wartecmod.entity.missile.EntityMissileAntiAirTier1;
import com.wartec.wartecmod.entity.missile.EntityMissileAntiBallisticNuclear;
import com.wartec.wartecmod.entity.missile.EntitySupersonicCruiseMissileHE;
import com.wartec.wartecmod.entity.missile.EntitySupersonicCruiseMissileH;
import com.wartec.wartecmod.entity.missile.EntityTomahawkMissile;
import com.wartec.wartecmod.entity.missile.EntityCruiseMissileTB;
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.lib.RefStrings;
import com.wartec.wartecmod.tileentity.launcher.TileEntityLaunchTube;

import api.hbm.item.IDesignatorItem;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class VerticalLauncher extends BlockContainer implements IBomb {

	public TileEntityLaunchTube tetn = new TileEntityLaunchTube();
	public static boolean keepInventory = false;
	private final static Random field_149933_a = new Random();

	public VerticalLauncher(Material p_i45386_1_) {
		super(p_i45386_1_);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityLaunchTube();
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(wartecmodBlocks.LaunchTube);
	}

	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		if(!keepInventory) {
			TileEntityLaunchTube tileentityfurnace = (TileEntityLaunchTube) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

			if(tileentityfurnace != null) {
				for(int i1 = 0; i1 < tileentityfurnace.getSizeInventory(); ++i1) {
					ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);

					if(itemstack != null) {
						float f = VerticalLauncher.field_149933_a.nextFloat() * 0.8F + 0.1F;
						float f1 = VerticalLauncher.field_149933_a.nextFloat() * 0.8F + 0.1F;
						float f2 = VerticalLauncher.field_149933_a.nextFloat() * 0.8F + 0.1F;

						while(itemstack.stackSize > 0) {
							int j1 = VerticalLauncher.field_149933_a.nextInt(21) + 10;

							if(j1 > itemstack.stackSize) {
								j1 = itemstack.stackSize;
							}

							itemstack.stackSize -= j1;
							EntityItem entityitem = new EntityItem(p_149749_1_, p_149749_2_ + f, p_149749_3_ + f1, p_149749_4_ + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

							if(itemstack.hasTagCompound()) {
								entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (float) VerticalLauncher.field_149933_a.nextGaussian() * f3;
							entityitem.motionY = (float) VerticalLauncher.field_149933_a.nextGaussian() * f3 + 0.2F;
							entityitem.motionZ = (float) VerticalLauncher.field_149933_a.nextGaussian() * f3;
							p_149749_1_.spawnEntityInWorld(entityitem);
						}
					}
				}

				p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
			}
		}

		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			TileEntityLaunchTube entity = (TileEntityLaunchTube) world.getTileEntity(x, y, z);
			if(entity != null) {
				FMLNetworkHandler.openGui(player, com.wartec.wartecmod.wartecmod.instance, wartecmodBlocks.guiID_LaunchTube, world, x, y, z);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onNeighborBlockChange(World p_149695_1_, int x, int y, int z, Block p_149695_5_) {
		if(p_149695_1_.isBlockIndirectlyGettingPowered(x, y, z) && !p_149695_1_.isRemote) {
			this.explode(p_149695_1_, x, y, z);
		}
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
		int i = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if(i == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if(i == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if(i == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		if(i == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
	}

	/*
	 * @Override public void setBlockBoundsBasedOnState(IBlockAccess
	 * p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) { float f
	 * = 0.0625F; this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F); }
	 * 
	 * @Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World
	 * world, int x, int y, int z) { float f = 0.0625F;
	 * this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 8*f, 1.0F); return
	 * AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ,
	 * x + this.maxX, y + this.maxY, z + this.maxZ); }
	 */
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemFromBlock(wartecmodBlocks.LaunchTube);
	}

	@Spaghetti("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA *takes breath* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
	@Override
	public BombReturnCode explode(World world, int x, int y, int z) {
		

		TileEntityLaunchTube entity = (TileEntityLaunchTube) world.getTileEntity(x, y, z);
		
		if(entity.slots[0] == null || world.isRemote)
			return BombReturnCode.ERROR_MISSING_COMPONENT;
		
		if(entity.slots[1] != null && entity.slots[1].getItem() instanceof IDesignatorItem && entity.power >= 75000) {
			
			if(!((IDesignatorItem)entity.slots[1].getItem()).isReady(world, entity.slots[1], x, y, z))
				return BombReturnCode.ERROR_MISSING_COMPONENT;
			
			int xCoord = entity.slots[1].stackTagCompound.getInteger("xCoord");
			int yCoord = entity.slots[1].stackTagCompound.getInteger("yCoord");
			int zCoord = entity.slots[1].stackTagCompound.getInteger("zCoord");

			if(xCoord == entity.xCoord && zCoord == entity.zCoord ) {
				xCoord += 6;
					
			}
			
			Entity missile = null;
            //Regular Cruise Missiles
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileHe) {
				missile = new EntityCruiseMissileHE(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileBuster) {
				missile = new EntityCruiseMissileBuster(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileCluster) {
				missile = new EntityCruiseMissileCluster(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileEmp) {
				missile = new EntityCruiseMissileEmp(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileTB) {
				missile = new EntityCruiseMissileTB(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileNuclear) {
				missile = new EntityCruiseMissileNuclear(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCruiseMissileH) {
				missile = new EntityCruiseMissileH(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemHypersonicCruiseMissileHE) {
				missile = new EntityHypersonicCruiseMissileHE(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemHypersonicCruiseMissileNuclear) {
				missile = new EntityHypersonicCruiseMissileNuclear(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemSupersonicCruiseMissileHE) {
				missile = new EntitySupersonicCruiseMissileHE(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemSupersonicCruiseMissileH) {
				missile = new EntitySupersonicCruiseMissileH(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			//Extra Cruise Missiles
			if(entity.slots[0].getItem() == wartecmodItems.itemTomahawkMissile) {
				missile = new EntityTomahawkMissile(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemKalibrMissile) {
				missile = new EntityKalibrMissile(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			if(entity.slots[0].getItem() == wartecmodItems.itemCj10Missile) {
				missile = new EntityCJ10Missile(world, x + 0.5F, y + 10F, z + 0.5F, xCoord, zCoord);
			}
			
			//if(missile instanceof EntityCruiseMissileBase) && //Object.isSubsonic && Object.Range > 3500)
				//return BombReturnCode.ERROR_INCOMPATIBLE;
			//if(missile instanceof EntityCruiseMissileBase) && Object.isSupersonic && Object.Range > 2000)
				//return BombReturnCode.ERROR_INCOMPATIBLE;
			//if(missile instanceof EntityCruiseMissileBase && Object.isHypersonic && Object.Range > 1250)
				//return BombReturnCode.ERROR_INCOMPATIBLE;
			//if(missile instanceof EntityCruiseMissileBase && Object.Range < 250)
				//return BombReturnCode.ERROR_INCOMPATIBLE;
			
			
			if(missile != null) {
				world.spawnEntityInWorld(missile);
				world.playSoundEffect(x, y+10, z, "wartecmod:weapon.CruiseMissileTakeoff", 10.0F, 1.0F);
				ExplosionLarge.spawnParticles(world, x, y + 11F, z, 5);
				entity.power -= 50000;
				entity.slots[0] = null;
	
				if(GeneralConfig.enableExtendedLogging)
					MainRegistry.logger.log(Level.INFO, "[MISSILE] Tried to launch missile at " + x + " / " + y + " / " + z + " to " + xCoord + " / " + zCoord + "!");
				return BombReturnCode.LAUNCHED;
			}
		}
		
		if(entity.slots[0] != null && entity.slots[0].getItem() == ModItems.missile_anti_ballistic && entity.power >= 75000) {
			EntityMissileAntiBallistic missile = new EntityMissileAntiBallistic(world);
			missile.posX = x + 0.5F;
			missile.posY = y + 0.5F;
			missile.posZ = z + 0.5F;
			
			world.spawnEntityInWorld(missile);
			entity.power -= 75000;

			entity.slots[0] = null;
			world.playSoundEffect(x, y, z, "hbm:weapon.missileTakeOff", 2.0F, 1.0F);
			return BombReturnCode.LAUNCHED;
		}
		
		if(entity.slots[0] != null && entity.slots[0].getItem() == wartecmodItems.itemMissileAntiBallisticNuclear && entity.power >= 5000) {
			EntityMissileAntiBallisticNuclear missile = new EntityMissileAntiBallisticNuclear(world);
			missile.posX = x + 0.5F;
			missile.posY = y + 0.5F;
			missile.posZ = z + 0.5F;
			
			world.spawnEntityInWorld(missile);
			entity.power -= 5000;

			entity.slots[0] = null;
			world.playSoundEffect(x, y, z, "hbm:weapon.missileTakeOff", 2.0F, 1.0F);
			return BombReturnCode.LAUNCHED;
		}
		if(entity.slots[0] != null && entity.slots[0].getItem() == wartecmodItems.itemMissileAntiAirTier1 && entity.power >= 50000) {
			EntityMissileAntiAirTier1 missile = new EntityMissileAntiAirTier1(world);
			missile.posX = x + 0.5F;
			missile.posY = y + 0.5F;
			missile.posZ = z + 0.5F;
			
			world.spawnEntityInWorld(missile);
			entity.power -= 5000;

			entity.slots[0] = null;
			world.playSoundEffect(x, y, z, "hbm:weapon.missileTakeOff", 2.0F, 1.0F);
			return BombReturnCode.LAUNCHED;
		}
		
		
		
		return BombReturnCode.ERROR_MISSING_COMPONENT;
	}
}