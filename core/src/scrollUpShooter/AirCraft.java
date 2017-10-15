package scrollUpShooter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;

import java.util.LinkedList;
import java.util.ListIterator;

public class AirCraft extends Actor {

    private AssetManager assetManager;
    private Pool<AircraftBullet> barrel;
    private LinkedList<AircraftBullet> bullets;

    public AirCraft() {
        setBounds(0, 0, 48, 48);
        setPosition(0, 0);
        barrel = new Pool<AircraftBullet>() {
            @Override
            protected AircraftBullet newObject() {
                return new AircraftBullet();
            }
        };
        bullets = new LinkedList<>();
    }

    @Override
    public void act(float delta) {
        ListIterator<AircraftBullet> bulls = bullets.listIterator();
        while (bulls.hasNext()) {
            AircraftBullet b = bulls.next();
            if (b.getY() >= Constants.SCREEN_HEIGHT || b.isHit()) {
                bulls.remove();
            } else {
                b.act(delta);
            }
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bullets.forEach(e -> e.draw(batch, parentAlpha));
        Texture texture = (Texture) assetManager.get("core/assets/aircraft.png");
        batch.draw(texture, getX(), getY());
    }

    public void shoot() {
        AircraftBullet bullet = barrel.obtain();
        bullet.setAssetManager(assetManager);
        bullet.setX(getX() + (getWidth() / 2));
        bullet.setY(getY() + (getHeight() / 2));
        bullet.addAction(Actions.moveTo(bullet.getX(), Constants.SCREEN_HEIGHT,
                Constants.BULLET_DURATION_BY_Y));
        bullets.add(bullet);
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

}
