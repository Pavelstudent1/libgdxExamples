package scrollUpShooter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyAircraft extends Actor{

    private AssetManager assetManager;
    private Sprite sprite;

    public EnemyAircraft(){
        setBounds(0, 0, 48, 48);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }

    public void setAssetManager(AssetManager assetManager) {
        Texture texture = (Texture) assetManager.get("core/assets/aircraft.png");
        this.assetManager = assetManager;
        this.sprite = new Sprite(texture);
        this.sprite.flip(false, true);
    }
}
