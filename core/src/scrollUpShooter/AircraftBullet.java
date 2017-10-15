package scrollUpShooter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AircraftBullet extends Actor {

    private AssetManager assetManager;
    private boolean hit;

    public AircraftBullet(){
        setBounds(0,0,3,3);
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw((Texture) assetManager.get("core/assets/bullet.png"), getX(), getY());
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
