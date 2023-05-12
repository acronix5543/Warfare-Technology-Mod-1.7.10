package com.wartec.wartecmod.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.bomb.TileEntityLaunchPad;
import com.wartec.wartecmod.tileentity.launcher.TileEntityLaunchTube;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderTileEntityLaunchTube extends TileEntitySpecialRenderer {

    @Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        
        bindTexture(com.wartec.wartecmod.ResourceManager.launcher_tex);
        com.wartec.wartecmod.ResourceManager.launchTube.renderAll();

        GL11.glDisable(GL11.GL_CULL_FACE);
        int state = 0;
        
        if(tileEntity instanceof TileEntityLaunchTube)
        	state = ((TileEntityLaunchTube)tileEntity).state;
        
	        GL11.glTranslated(0, 1, 0);
	        
			if(state == 1)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 2)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_buster_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 3)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Cluster_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 4)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Gas_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 5)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Emp_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 6)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_FAE_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 7)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_nuclear_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 8)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_H_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 9)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslated(0, 0, 0);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Hypersonic_Cruise_Missile_tex);
				com.wartec.wartecmod.ResourceManager.entity_Hypersonic_Cruise_Missile_Scramjet.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Hypersonic_Cruise_Missile_Booster.renderAll();
				
			}
			if(state == 10)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslated(0, 0, 0);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Ramjet_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Ramjet.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Fuselage_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Fuselage.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Engine_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Engine.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Booster_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Booster.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Protection_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Protection.renderAll();
				
			}
			if(state == 11)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslated(0, 0, 0);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Tomahawk_Missile_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
				
			}
			if(state == 12)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslated(0, 0, 0);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Kalibr_Missile_tex);
				com.wartec.wartecmod.ResourceManager.entity_Kalibr_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Kalibr_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Kalibr_Missile_Fins_Folded.renderAll();
				
			}
			if(state == 13)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslated(0, 0, 0);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Ramjet_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Ramjet.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Fuselage_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Fuselage.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Engine_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Engine.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Booster_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Booster.renderAll();
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Protection_tex);
				com.wartec.wartecmod.ResourceManager.entity_Supersonic_Cruise_Missile_Protection.renderAll();
				
			}
			if(state == 14)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				GL11.glTranslated(0, 0, 0);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Hypersonic_Cruise_Missile_Nuclear_tex);
				com.wartec.wartecmod.ResourceManager.entity_Hypersonic_Cruise_Missile_Scramjet.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Hypersonic_Cruise_Missile_Booster.renderAll();
				
			}
			if(state == 15)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_nuclear_tex);
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Base.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Booster.renderAll();
				com.wartec.wartecmod.ResourceManager.entity_Cruise_Missile_Sealing.renderAll();
			}
			if(state == 16)
			{
				GL11.glScalef(1.0F, 1.0F, 1.0F);
				bindTexture(com.wartec.wartecmod.ResourceManager.entity_Missile_Anti_Air_Tier1_tex);
				com.wartec.wartecmod.ResourceManager.entity_Missile_Anti_Air_Tier1.renderAll();
			}
			
			
	        GL11.glEnable(GL11.GL_CULL_FACE);

        GL11.glPopMatrix();
    }

}