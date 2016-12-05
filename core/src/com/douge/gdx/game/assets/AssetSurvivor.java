package com.douge.gdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class AssetSurvivor
{

	public final AtlasRegion survivor;
	public Animation runAnimation;
	public Animation standingAnimation;
	public Animation fallingAnimation;
	public Animation jumpingAnimation;
	public Animation dashingAnimation;
	public final Animation humanSAnimation;
	public final Animation humanFAnimation;
	public final Animation humanJAnimation;
	public final Animation humanDAnimation;
	public final Animation humanRAnimation;
	public final Animation hurtAnimation;
	public final ParticleEffect dustParticles;
	public final ParticleEffect dustJumpParticles;
	public final Animation jumpAttackAnimation;
	public final Animation groundedAttackAnimation;
	public final Animation wizard;
	public final TextureAtlas atlas;
	
	protected AssetSurvivor(TextureAtlas atlas) 
	{
		this.atlas = atlas;
		survivor = atlas.findRegion("survivor0");
		
		// Particles
		dustParticles = new ParticleEffect();
		dustParticles.load(Gdx.files.internal("../core/assets/particles/dust.pfx"), Gdx.files.internal("../core/assets/particles"));
		dustJumpParticles = new ParticleEffect();
		dustJumpParticles.load(Gdx.files.internal("../core/assets/particles/dustJump.pfx"), Gdx.files.internal("../core/assets/particles"));
		
		Array<AtlasRegion> regions = new Array<AtlasRegion>();
		
		for(int i = 0; i <= 2; i++)
		{
			regions.add(atlas.findRegion("survivor0"+i));
		}
		standingAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor6"));
		fallingAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor1"));
		jumpingAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		for(int i = 19; i <= 24; i++)
		{
			regions.add(atlas.findRegion("survivor"+i));
		}
		runAnimation = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor19"));
		dashingAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor11"));
		hurtAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		for(int i = 2; i <= 5; i++)
		{
			regions.add(atlas.findRegion("survivor"+i));
		}
		groundedAttackAnimation = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.NORMAL);
		
		regions = new Array<AtlasRegion>();
		for(int i = 8; i <= 10; i++)
		{
			regions.add(atlas.findRegion("survivor"+i));
		}
		jumpAttackAnimation = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.NORMAL);
		
		regions = new Array<AtlasRegion>();
		for(int i = 0; i <= 2; i++)
		{
			regions.add(atlas.findRegion("wizard"+i));
		}
		wizard = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP);
		
		
		regions = new Array<AtlasRegion>();
		regions.add(atlas.findRegion("survivor000"));
		
		humanSAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor006"));
		humanFAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor001"));
		humanJAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		for(int i = 19; i <= 23; i++)
		{
			regions.add(atlas.findRegion("survivor0"+i));
		}
		humanRAnimation = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP);
		
		regions = new Array<AtlasRegion>();
		
		regions.add(atlas.findRegion("survivor019"));
		humanDAnimation = new Animation(1.0f/10.0f, regions, Animation.PlayMode.LOOP);
		
	}

}
