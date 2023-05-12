package com.wartec.wartecmod.packet;

import com.hbm.interfaces.Spaghetti;
import com.wartec.wartecmod.items.wartecmodItems;
import com.wartec.wartecmod.tileentity.launcher.TileEntityBallisticMissileLauncher;
import com.wartec.wartecmod.tileentity.launcher.TileEntityLaunchTube;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TELaunchTubePacket implements IMessage {

	int x;
	int y;
	int z;
	int type;

	public TELaunchTubePacket() { }

	@Spaghetti("die")
	public TELaunchTubePacket(int x, int y, int z, ItemStack stack) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = 0;
		if(stack != null) {
			if(stack.getItem() == wartecmodItems.itemCruiseMissileHe)
				type = 1;
			if(stack.getItem() == wartecmodItems.itemCruiseMissileBuster)
				type = 2;
			if(stack.getItem() == wartecmodItems.itemCruiseMissileCluster)
				type = 3;
			if(stack.getItem() == wartecmodItems.itemCruiseMissileEmp)
				type = 5;
			if(stack.getItem() == wartecmodItems.itemCruiseMissileTB)
				type = 6;
			if(stack.getItem() == wartecmodItems.itemCruiseMissileNuclear)
				type = 7;
			if(stack.getItem() == wartecmodItems.itemCruiseMissileH)
				type = 8;
			if(stack.getItem() == wartecmodItems.itemHypersonicCruiseMissileHE)
				type = 9;
			if(stack.getItem() == wartecmodItems.itemSupersonicCruiseMissileHE)
				type = 10;
			if(stack.getItem() == wartecmodItems.itemTomahawkMissile)
				type = 11;
			if(stack.getItem() == wartecmodItems.itemKalibrMissile)
				type = 12;
			if(stack.getItem() == wartecmodItems.itemSupersonicCruiseMissileH)
				type = 13;
			if(stack.getItem() == wartecmodItems.itemHypersonicCruiseMissileNuclear)
				type = 14;
			if(stack.getItem() == wartecmodItems.itemMissileAntiBallisticNuclear)
				type = 15;
			if(stack.getItem() == wartecmodItems.itemMissileAntiAirTier1)
				type = 16;
			
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		type = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(type);
	}

	public static class Handler implements IMessageHandler<TELaunchTubePacket, IMessage> {
		
		@Override
		public IMessage onMessage(TELaunchTubePacket m, MessageContext ctx) {
			
			try {
				TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(m.x, m.y, m.z);
	
				if (te != null && te instanceof TileEntityLaunchTube) {
						
					TileEntityLaunchTube gen = (TileEntityLaunchTube)te;
					gen.state = m.type;
				}
			} catch(Exception e) { }
			
			return null;
		}
	}
}
