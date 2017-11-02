package scrollUpShooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SimpleScrollUpShooter extends ApplicationAdapter {

    private StageFirst stage;
    private AirCraft mainActor;
    private InputProcessor inputHandler;
    private AssetManager manager = new AssetManager();

    @Override
    public void create() {
        manager.load("core/assets/BSP_054_mod.png", Texture.class);
        manager.load("core/assets/aircraft.png", Texture.class);
        manager.load("core/assets/bullet.png", Texture.class);
        manager.finishLoading();

        mainActor = new AirCraft();
        mainActor.setAssetManager(manager);

        stage = new StageFirst(mainActor);
        stage.setAssetManager(manager);

        EnemyAircraft actor2 = new EnemyAircraft();
        actor2.setAssetManager(manager);
        actor2.setPosition(200, 400);

        stage.addActor(actor2);

        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stage.setDebugAll(false);
        stage.setViewport(new FitViewport(
                Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, cam));
        inputHandler = new InputHandler(stage, mainActor);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
