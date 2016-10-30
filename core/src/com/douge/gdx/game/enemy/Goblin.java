package com.douge.gdx.game.enemy;

import com.douge.gdx.game.VIEW_DIRECTION;
import com.douge.gdx.game.assets.enemy.AssetGoblin;

public class Goblin extends Enemy
{
	public Goblin(AssetGoblin assets) 
	{
		super(assets, 1f, VIEW_DIRECTION.LEFT);
		int multiplier = 4;
		dimension.x *= multiplier;
		dimension.y *= multiplier;
		bounds.height = dimension.y - 1;
		bounds.width += (.25f * dimension.x);
		bounds.x += (.25f * dimension.x);
	}
}
