package actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import utils.Const;

public class Ground extends Actor{
	
	Body body;
	int width = 100;
	int height = 50;
	
	public Ground(World world) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		bd.position.set(new Vector2(Const.GROUND_INIT_X, Const.GROUND_INIT_Y));
		
		body = world.createBody(bd);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		body.createFixture(shape, 0);
		shape.dispose();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	
}
