package com.douge.gdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.douge.gdx.game.VIEW_DIRECTION;
import com.douge.gdx.game.assets.Assets;

public class Crow extends AbstractGameObject
{
	private Animation flying;
	private Player player;
	
	public Crow(Player player)
	{
		this.player = player;
		init();
	}
	
	private void init()
	{
		dimension.set(1f, 1f);
		flying = Assets.instance.crow.flying;
		stateTime = 0f;
		origin.x = dimension.x/2;
		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
	}
	
	@Override
	public void update(float deltaTime)
	{
		stateTime += deltaTime;
		float xOffset = player.viewDirection == VIEW_DIRECTION.RIGHT ? -dimension.x : player.dimension.x;
		Vector2 newPos = new Vector2(player.position.x + xOffset, player.position.y);
		position.lerp(newPos, 1f * deltaTime);
	}
	
	@Override
	public void render(SpriteBatch batch) 
	{
		TextureRegion reg = flying.getKeyFrame(stateTime);
		batch.draw(reg.getTexture(), 
				position.x, position.y+.5f,
				origin.x, origin.y, 
				dimension.x, dimension.y, 
				scale.x, scale.y,
				rotation, 
				reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), 
				position.x + origin.x > player.position.x + player.origin.x, false);
	}

}
