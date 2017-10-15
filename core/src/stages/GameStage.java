package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actors.Ground;
import actors.Player;
import utils.Collisions;
import utils.Const;
import utils.InputKeyboard;

public class GameStage extends Stage {
	
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	
	private World world;
	private Ground ground;
	private Ground ground2;
	private Player player;
	private float accumulator = 0;
	private InputProcessor keyboard;

	public GameStage() {
		camera = new OrthographicCamera(Const.APP_WIDTH, Const.APP_HEIGHT);
		camera.position.set(50, 50, 0);
		camera.update();
		
		renderer = new Box2DDebugRenderer();
		
		world = new World(Const.WORLD_GRAVITY, true);
		ground = new Ground(world);
		ground2 = new Ground(world);
		ground2.setPosition(Const.GROUND_INIT_X + 150, 0f, 0f);
		ground2.startLeftRightMove(10, 0.5f);
		
		player = new Player(world);
		addActor(ground);
		addActor(ground2);
		addActor(player);
		
		keyboard = new InputKeyboard(player);
		Gdx.input.setInputProcessor(keyboard);

		world.setContactListener(new Collisions(player, ground));
	}
	
	@Override
	public void act(float delta) {
		accumulator += delta;
		super.act(delta);
		world.step(delta, 6, 2);
	}
	
	@Override
	public void draw() {
		super.draw();
		renderer.render(world, camera.combined);
	}
	
	
	
	

}
