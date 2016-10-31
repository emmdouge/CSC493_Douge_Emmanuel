package com.douge.gdx.game.playerstate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.douge.gdx.game.assets.Assets;
import com.douge.gdx.game.objects.Platform;
import com.douge.gdx.game.objects.Player;
import com.douge.gdx.game.VIEW_DIRECTION;

public class DashState extends PlayerState
{

	public DashState(Player astronaut, PlayerStateContext context) {
		super(astronaut, context);
	}

	@Override
	public void execute(float deltaTime) 
	{
		//Gdx.app.log(tag, "" + player.timeDashing);
		// Keep track of jump time
		player.timeDashing += deltaTime;
		player.currentAnimation = Assets.instance.survivor.dashingAnimation;
		if(player.timeDashing <= player.DASH_TIME_MAX)
		{
			player.afterImageDash.addNode(player, player.currentAnimation.getKeyFrame(player.stateTime));
			int direction = VIEW_DIRECTION.getInt(player.viewDirection);
			
			player.position.x += (10*direction) * deltaTime;
			player.currentVelocity.y = 0;
			player.currentVelocity.x = 0;
			
			if (player.timeLeftJumpPowerup > 0) 
			{
				player.timeLeftJumpPowerup -= deltaTime;
			}
			if (player.timeLeftJumpPowerup < 0) 
			{
				// disable power-up
				player.timeLeftJumpPowerup = 0;
				player.setJumpPowerup(false);
			}
		}
		else
		{
			player.timeJumping = player.JUMP_TIME_MAX;
			context.setPlayerState(context.getFallingState());
		}
	}

	@Override
	public void onCollisionWith(Platform platform) 
	{

		float diffBetweenRightSideOfPlayerAndLeftSideOfPlatform = platform.position.x - player.position.x - player.bounds.width;
		float diffBetweenLeftSideOfPlayerAndRightSideOfPlatform = platform.position.x + platform.bounds.width - player.position.x;
		float diffBetweenBottomOfPlayerAndTopOfPlatform = platform.position.y + platform.bounds.height - player.position.y;
		
		boolean hitLeftEdge = diffBetweenRightSideOfPlayerAndLeftSideOfPlatform <= 0.07f;
		boolean hitRightEdge = diffBetweenLeftSideOfPlayerAndRightSideOfPlatform <= 1f;
		boolean onTopOfRock =  diffBetweenBottomOfPlayerAndTopOfPlatform <= 0.07f;

		//Gdx.app.log(tag, "rock: " + rock.position.x + "+" + rock.bounds.width + "=" + (rock.position.x+rock.bounds.width) + ", player: " + player.position.x + " " + diffBetweenLeftSideOfPlayerAndRightSideOfRock);
		if(onTopOfRock)
		{
			player.position.y += .01f;
		}
		else if(hitLeftEdge)
		{
			//Gdx.app.log(tag, "rock: " + rock.position.x + "+" + rock.bounds.width + "=" + (rock.position.x+rock.bounds.width) + ", player: " + player.position.x + " " + diffBetweenRightSideOfPlayerAndLeftSideOfRock);
			player.currentFriction = 0;
			player.currentVelocity.x = 0;
			
			//since the rocks are all linked together, rock's bound witdth is the entire platform
			player.position.x = platform.position.x - 1;
		}
		if(hitRightEdge)
		{
			System.out.println("im here");
			player.currentFriction = 0;
			player.currentVelocity.x = 0;
			player.position.x = platform.position.x + platform.bounds.width;
		}
	}

	@Override
	public void noPlatformCollision() 
	{
		context.noPlatformCollision();
	}

}
