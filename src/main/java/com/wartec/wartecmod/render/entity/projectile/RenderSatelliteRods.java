package com.wartec.wartecmod.render.entity.projectile;

import com.wartec.wartecmod.ResourceManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSatelliteRods extends Render {

	public RenderSatelliteRods() { }

	@Override
	public void doRender(Entity missile, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
		GL11.glRotatef(missile.prevRotationYaw + (missile.rotationYaw - missile.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(missile.prevRotationPitch + (missile.rotationPitch - missile.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
		
		GL11.glScalef(1.5F, 1.5F, 1.5F);

		bindTexture(ResourceManager.entity_Rod_Tungsten_tex);
		ResourceManager.entity_Rod_Tungsten.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return ResourceManager.entity_Rod_Tungsten_tex;
	}

}