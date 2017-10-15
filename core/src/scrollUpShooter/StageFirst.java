package scrollUpShooter;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class StageFirst extends Stage {

    private Actor mainActor;
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    private AssetManager assetManager;

    public StageFirst(Actor mainActor) {
        this.mainActor = mainActor;
        addActor(mainActor);
    }

    @Override
    public void act(float delta) {
        checkCollisions();
        super.act(delta);
    }

    private void checkCollisions() {
        Array<Actor> actors = getActors();
        Rectangle fixture1 = rectPool.obtain();
        fixture1.set(mainActor.getX(), mainActor.getY(), mainActor.getWidth(), mainActor
                .getHeight());
        for (int i = 1; i < actors.size; i++) {
            Actor actor = actors.get(i);
            Rectangle fixture2 = rectPool.obtain();
            fixture2.set(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
            boolean isOverlap = Intersector.overlaps(fixture1, fixture2);
            if (isOverlap) {
                System.out.println("Overlap!");
            }
        }
    }

    @Override
    public void draw() {
        getBatch().begin();
        getBatch().draw((Texture) assetManager.get("core/assets/BSP_054_mod.png"), 0, 0);
        getBatch().end();
        super.draw();
    }


    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

}
