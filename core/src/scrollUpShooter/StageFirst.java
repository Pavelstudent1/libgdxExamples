package scrollUpShooter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Iterator;

public class StageFirst extends Stage {

    private Actor mainActor;
    private Pool<Rectangle> rectPool;
    private AssetManager assetManager;
    private Array<AircraftBullet> bullets;
    private Array<EnemyAircraft> enemies;

    public StageFirst(Actor mainActor) {
        this.mainActor = mainActor;
        rectPool = new Pool<Rectangle>() {
            @Override
            protected Rectangle newObject() {
                return new Rectangle();
            }
        };
        addActor(mainActor);
        bullets = new Array<>();
        enemies = new Array<>();
    }

    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);

        if (actor instanceof AircraftBullet) {
            bullets.add((AircraftBullet) actor);
        } else if (actor instanceof EnemyAircraft) {
            enemies.add((EnemyAircraft) actor);
        }
    }

    @Override
    public void act(float delta) {
        checkCollisions();
        super.act(delta);
    }

    private void checkCollisions() {
        /**
         * Each bullet should check collision with each enemy vessel
         */
        for (AircraftBullet bullet : bullets) {
            if (bullet.getY() >= Constants.SCREEN_HEIGHT) {
                removeActor(bullet);
//                bullet.remove();
//                bullets.removeValue(bullet, false);
                continue;
            }
            Rectangle bulFixture = rectPool.obtain();
            bulFixture.set(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            for (EnemyAircraft enemy : enemies) {
                Rectangle enemyFix = rectPool.obtain();
                enemyFix.set(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

                boolean isOverlap = Intersector.overlaps(bulFixture, enemyFix);
                if (isOverlap) {
                    System.out.println("Bullet hit an enemy!");
//                    bullet.remove();
//                    bullets.removeValue(bullet, false);
//                    enemy.remove();
//                    enemies.removeValue(enemy, false);
                    removeActor(bullet);
                    removeActor(enemy);
                    break;
                }
            }
        }
    }

    public void removeActor(Actor actor) {
        if (actor instanceof AircraftBullet) {
            bullets.removeValue((AircraftBullet) actor, false);
        } else if (actor instanceof EnemyAircraft) {
            enemies.removeValue((EnemyAircraft) actor, false);
        }
        actor.remove();
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
