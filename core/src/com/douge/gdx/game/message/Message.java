package com.douge.gdx.game.message;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.douge.gdx.game.Constants;
import com.douge.gdx.game.assets.Assets;
import com.douge.gdx.game.objects.Player;

public class Message 
{
	private static final ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public String text;
	public String appendedText;
	public float maxHeight;
	public float currentHeight = 0f;
	public int textIndex = 0;
	public boolean playerSkipped = false;
	public float timeBetweenCharacters = 0;
	public Vector2 conditions;
	private Rectangle box;
	private boolean boxIsRendered = false;
	public boolean iconIsRendered = false;
	private Sprite icon;
	private float iconX;
	public boolean textIsRendered = false;
	public boolean shouldBeRendered = false;
	public Message nextNode;
	public float stateTime = 0;

	public boolean isRendering = false;

	public Message(String text, Vector2 conditions, TextureRegion reg) 
	{
		this(text, conditions, reg, 50f);
	}
	
	public Message(String text, Vector2 conditions, TextureRegion reg, float size) 
	{
		this.text = text;
		text += " ";
		this.conditions = conditions;
		//drawn from top left to bottom right
		box = new Rectangle(0, Constants.VIEWPORT_GUI_HEIGHT - size, Constants.VIEWPORT_GUI_WIDTH, 0);
		maxHeight = Constants.VIEWPORT_GUI_HEIGHT - box.y;
		if(reg != null)
		{
			icon = new Sprite(reg);
			icon.setRegion(reg, 0, 0, reg.getRegionWidth(), reg.getRegionWidth() / 2);
			iconX = size;
			icon.setX(iconX);
			icon.setY(box.y - 40f);
			icon.flip(false, true);
		}
	}

	public void updateText(float deltaTime, Player player) 
	{
		timeBetweenCharacters += deltaTime;
		if (timeBetweenCharacters >= .04f && boxIsRendered && iconIsRendered) 
		{
			textIndex++;
			timeBetweenCharacters = 0;
		}
		if(player != null)
		{
			if (player.position.x >= conditions.x && player.position.y >= conditions.y) 
			{
				shouldBeRendered = true;
			}
		}
		stateTime += deltaTime;
	}

	public void renderText(SpriteBatch batch) 
	{
		// Gdx.app.log("", "" + icon.getX() + " " + textIndex);
		if (playerSkipped == false && shouldBeRendered) 
		{
			renderBox(batch, currentHeight);
			renderIcon(batch);
			if (boxIsRendered && iconIsRendered) 
			{
				if (textIndex >= text.length()) 
				{
					textIndex = text.length();
					textIsRendered = true;
					renderButton(batch);
				}
				for (int i = 0; i <= textIndex; i++) 
				{
					BitmapFont fontGameOver = Assets.instance.fonts.defaultBig;
					fontGameOver.setColor(Color.BLACK);
					fontGameOver.getColor().set(Color.WHITE);
					fontGameOver.getData().setScale(1f, 1f);
					fontGameOver.draw(batch, text.subSequence(0, i), box.x + 100, box.y + 25, 0, Align.left, true);
				}
			} 
			else 
			{
				currentHeight += 1.5f;
				currentHeight = MathUtils.clamp(currentHeight, 0, maxHeight);
				if (currentHeight == maxHeight) 
				{
					boxIsRendered = true;
				}
				if(icon != null)
				{
					iconX += .75f;
					iconX = MathUtils.clamp(iconX, -50f, 0 + 1f);
					icon.setX(iconX);
					if (iconX >= 1f) 
					{
						iconIsRendered = true;
					}
				}
			}
		}

	}

	private void renderButton(SpriteBatch batch) 
	{
		TextureRegion reg = Assets.instance.tiles.buttonPress.getKeyFrame(stateTime, true);
		batch.draw(reg.getTexture(), 
				box.x + 525, box.y + 11, 
				0, 0, 
				100, 40, 
				1, 1, 
				0,
				reg.getRegionX(), reg.getRegionY(), 
				reg.getRegionWidth(), reg.getRegionHeight(), 
				false, false);		
	}

	private void renderIcon(SpriteBatch batch) 
	{
		if(icon != null)
		batch.draw(icon, iconX, icon.getY(), 0, 0, icon.getWidth(), icon.getHeight(), 4, 2, 0f);
	}

	private void renderBox(SpriteBatch batch, float currentHeight) {
		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		box.height = currentHeight;
		shapeRenderer.rect(box.x, box.y, box.width, box.height);
		shapeRenderer.end();
		batch.begin();
	}
}
