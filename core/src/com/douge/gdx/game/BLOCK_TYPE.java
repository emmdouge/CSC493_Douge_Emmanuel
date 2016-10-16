package com.douge.gdx.game;

public enum BLOCK_TYPE 
{
	ROCK_BACK(0, 0, 0), // black
	ROCK(0, 255, 0), // green
	PLAYER_SPAWNPOINT(255, 255, 255), // white
	ITEM_GREENHEART(255, 0, 255), // purple
	ITEM_GOLD_COIN(255, 255, 0), // yellow
	
	
	ENEMY_SLIME(34, 177, 76);
	
	private int color;
	
	private BLOCK_TYPE (int r, int g, int b) 
	{
		color = r << 24 | g << 16 | b << 8 | 0xff;
	}
	
	public boolean sameColor(int color) 
	{
		return this.color == color;
	}
		
	public int getColor () 
	{
		return color;
	}
	public static boolean validColor(int currentPixel) 
	{
		for (BLOCK_TYPE blockType : BLOCK_TYPE.values())
		{
			if(currentPixel == blockType.color)
			{
				return true;
			}
		}
		return false;
	}
}