package utils;

import com.badlogic.gdx.math.Vector2;

public class Const {
	
	public static final int APP_WIDTH = 800;
	public static final int APP_HEIGHT = 480;
	
	public static final int VIEW_WIDTH = 20;
	public static final int VIEW_HEIGHT = 10;
	
	public static final Vector2 WORLD_GRAVITY = new Vector2(0, -300f);
	
	public static final int GROUND_INIT_X = 0;
	public static final int GROUND_INIT_Y = 0;
	
	public static final int PLAYER_INIT_X = 0;
	public static final int PLAYER_INIT_Y = GROUND_INIT_Y + 100;
	public static final float PLAYER_JUMP = 300f;
	
	public static enum ACTOR_STATE{
		WALK, DEAD, INIT, IN_AIR, ON_GROUND
	}
	
	public static final short PLAYERS_COLLISION = 0x1;
	public static final short GROUNDS_COLLISION = 0x2;
}
