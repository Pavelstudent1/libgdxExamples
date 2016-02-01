package actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import utils.Const;
import utils.Const.ACTOR_STATE;

public class Player extends Actor{
	
	Body body;
	int width = 10;
	int height = 20;
	
	public ACTOR_STATE state;
	
	public boolean left = false, 
				   right = false,
				   jump = false;
	
	public Player(World world) {
		state = ACTOR_STATE.INIT;
		
		BodyDef bd = new BodyDef();
		bd.type = BodyDef.BodyType.DynamicBody;
		bd.position.set(Const.PLAYER_INIT_X, Const.PLAYER_INIT_Y);
		body = world.createBody(bd);
		
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(width / 2, height / 2);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = ps;
		fdef.density = 0.0f;
		fdef.restitution = 0.1f;
		fdef.filter.categoryBits = Const.PLAYERS_COLLISION;
		fdef.filter.maskBits = Const.PLAYERS_COLLISION | Const.GROUNDS_COLLISION;
		
		body.createFixture(fdef);
		ps.dispose();
	}
	
	@Override
	public void act(float delta) {
		
		if (state == ACTOR_STATE.INIT){
			state = ACTOR_STATE.IN_AIR;
		}
		
		if (left) stepLeft();
		if (right) stepRight();
		if (jump && state == ACTOR_STATE.ON_GROUND) jump();
		
		System.out.println("Player velocity: " + body.getLinearVelocity());
	}
	

	public void jump() {
		
		if (left || right){
			body.setLinearVelocity(body.getLinearVelocity().x, Const.PLAYER_JUMP);
		}else{
			body.setLinearVelocity(0f, 200f);			
		}
		state = ACTOR_STATE.IN_AIR;
		jump = false;
	}
	
	public void setGrounded(){
		state = ACTOR_STATE.ON_GROUND;
	}

	public void stepLeft() {
		
		float vel_x = body.getLinearVelocity().x;
		
		if (state == ACTOR_STATE.IN_AIR){
			if (vel_x < -80) return;
		}else{
			if (vel_x < -80) return;
		}
		body.applyLinearImpulse(new Vector2(-10, 0), body.getLocalCenter(), true);
	}

	public void stepRight() {
		float vel_x = body.getLinearVelocity().x;
		
		if (state == ACTOR_STATE.IN_AIR){
			if (vel_x > 80) return;
		}else{
			if (vel_x > 80) return;
		}
		body.applyLinearImpulse(new Vector2(10, 0), body.getLocalCenter(), true);
	}	
	
	
	
	
}
