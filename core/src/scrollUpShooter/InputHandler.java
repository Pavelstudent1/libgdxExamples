package scrollUpShooter;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class InputHandler implements InputProcessor {

    private StageFirst stage;
    private AirCraft actor;

    public InputHandler(StageFirst stage, AirCraft actor) {
        this.stage = stage;
        this.actor = actor;
    }

    @Override
    public boolean keyDown(int keycode) {
        final float plyX = actor.getX();
        final float plyY = actor.getY();

        final float effectWidth = Constants.SCREEN_WIDTH - actor.getWidth();
        float timeConstLeft =
                (actor.getX() * Constants.PLAYER_SHIFT_DURATION_BY_X) / effectWidth;
        float timeConstRight =
                ((Constants.SCREEN_WIDTH - actor.getWidth() - actor.getX()) *
                        Constants.PLAYER_SHIFT_DURATION_BY_X) / effectWidth;
        switch (keycode) {
            case Input.Keys.LEFT:
                if (plyX > 0) {
                    actor.addAction(Actions.moveTo(0, plyY, timeConstLeft));
                }
                break;
            case Input.Keys.RIGHT:
                if (plyX < effectWidth) {
                    actor.addAction(Actions.moveTo(effectWidth, plyY, timeConstRight));
                }
                break;
            case Input.Keys.SPACE:
                actor.shoot();
                break;
            case Input.Keys.SHIFT_LEFT:
                break;
            default:
                debugKeys(keycode);
        }

        return true;
    }

    private void debugKeys(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1:
                Constants.PLAYER_SHIFT_DURATION_BY_X -= 0.1f;
                break;
            case Input.Keys.NUM_2:
                Constants.PLAYER_SHIFT_DURATION_BY_X += 0.1f;
            case Input.Keys.NUM_3:
                Constants.BULLET_DURATION_BY_Y -= 0.1f;
                break;
            case Input.Keys.NUM_4:
                Constants.BULLET_DURATION_BY_Y += 0.1f;
                break;
        }
        System.out.println("Player movement: " + Constants.PLAYER_SHIFT_DURATION_BY_X);
        System.out.println("Bullet movement: " + Constants.BULLET_DURATION_BY_Y);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                actor.getActions().clear();
                break;
            case Input.Keys.RIGHT:
                actor.getActions().clear();
                break;
            case Input.Keys.SPACE:
                break;
            case Input.Keys.SHIFT_LEFT:
                break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Array<Actor> actors = stage.getActors();
        Actor actor = null;
        boolean overlap = false;
        for (Actor a : actors) {
            Rectangle ac = new Rectangle(a.getX(), a.getY(), a.getWidth(), a.getHeight());
            overlap = ac.contains(screenX, Constants.SCREEN_HEIGHT - screenY);
            if (overlap) {
                actor = a;
                break;
            }
        }

        if (overlap){
            actor.setX(screenX - actor.getWidth() / 2);
            actor.setY(Constants.SCREEN_HEIGHT - screenY - actor.getHeight() / 2);
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        System.out.println("Coord X=" + screenX + " Y=" + screenY);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
