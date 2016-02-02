package actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import utils.Const;

public class Ground extends Actor{
	
	Body body;
	int width = 100;
	int height = 50;
	
	public static enum GROUND_STATE {STATIC, MOVING};
	
	private boolean startMoving = false,
					left = true,
					right = false;
	private float step = 0;
	private float max_x = 0, min_x = 0;
	
	public Ground(World world) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(new Vector2(Const.GROUND_INIT_X, Const.GROUND_INIT_Y));
		
		body = world.createBody(bd);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.density = 0.5f;
		fdef.restitution = 0.1f;
		fdef.friction = 0.5f;
		fdef.filter.categoryBits = Const.GROUNDS_COLLISION;
		fdef.filter.maskBits = Const.GROUNDS_COLLISION | Const.PLAYERS_COLLISION;
		
		body.createFixture(fdef);
		shape.dispose();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (startMoving){
			float x = body.getPosition().x, y = body.getPosition().y;
			if (left){
				body.setTransform(x - step, y, 0);
				if (body.getPosition().x <= min_x){
					left = false;
				}
			}else{
				body.setTransform(x + step, y, 0);
				if (body.getPosition().x >= max_x){
					left = true;
				}
			}	
		}
		
	}
	
	public void setPosition(float x, float y, float angle){
		body.setTransform(x, y, angle);
	}
	
	public void startLeftRightMove(float bias, float step){
		this.startMoving = true;
		this.step = step;
		min_x = body.getPosition().x - bias;
		max_x = body.getPosition().x + bias;
	}
	
}
