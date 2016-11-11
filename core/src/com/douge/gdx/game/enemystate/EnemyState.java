package com.douge.gdx.game.enemystate;

import com.douge.gdx.game.objects.Platform;
import com.douge.gdx.game.objects.Player;
import com.douge.gdx.game.objects.enemy.Enemy;

public abstract class EnemyState 
{
	public Enemy enemy;
	public EnemyStateContext context;
	public String tag;
	
	public EnemyState(Enemy enemy, EnemyStateContext context)
	{
		this.enemy = enemy;
		this.context = context;
	}

	public abstract void execute(float deltaTime);
	public abstract void onCollisionWith(Platform rock);
	public abstract void noRockCollision();
}
