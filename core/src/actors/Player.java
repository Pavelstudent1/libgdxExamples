package actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import utils.Const;
import utils.Const.ACTOR_STATE;

public class Player extends Actor{
	
	Body body;
	int width = 10;
	int height = 20;
	
	ACTOR_STATE state;
	
	public Player(World world) {
		state = ACTOR_STATE.INIT;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyDef.BodyType.DynamicBody;
		bd.position.set(Const.PLAYER_INIT_X, Const.PLAYER_INIT_Y);
		body = world.createBody(bd);
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(width / 2, height / 2);
		body.createFixture(ps, 0.5f);
		body.resetMassData();
		ps.dispose();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (state == ACTOR_STATE.INIT){
			//body.applyForce(Const.WORLD_GRAVITY, new Vector2(), true);
			state = ACTOR_STATE.IN_AIR;
		}
		
		
		
	}

	public void jump() {
		body.setLinearVelocity(0f, 50f);
	}
	
	
	
	
	
}
