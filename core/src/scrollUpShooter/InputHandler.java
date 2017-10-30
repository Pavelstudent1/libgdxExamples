package scrollUpShooter;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputHandler implements InputProcessor {

    private StageFirst stage;
    private AirCraft actor;

    private Action lastMoveAction;

    public InputHandler(StageFirst stage, AirCraft actor) {
        this.stage = stage;
        this.actor = actor;
    }

    @Override
    public boolean keyDown(int keycode) {
        final float plyX = actor.getX();
        final float plyY = actor.getY();

        final float effectWidth = Constants.SCREEN_WIDTH - actor.getWidth();
        /**
         * Idea: we know for sure, how fast the aircraft should move from left to right.
         * So, according to this time, we could calculate how much time it need in at
         * each concrete position.
         */
        float timeConstLeft =
                (actor.getX() * Constants.PLAYER_SHIFT_DURATION_BY_X) / effectWidth;
        float timeConstRight =
                ((Constants.SCREEN_WIDTH - actor.getWidth() - actor.getX()) *
                        Constants.PLAYER_SHIFT_DURATION_BY_X) / effectWidth;

        switch (keycode) {
            case Input.Keys.LEFT:
                if (plyX > 0) {
                    lastMoveAction = Actions.moveTo(0, plyY, timeConstLeft);
                    actor.addAction(lastMoveAction);
                }
                break;
            case Input.Keys.RIGHT:
                if (plyX < effectWidth) {
                    lastMoveAction = Actions.moveTo(effectWidth, plyY, timeConstRight);
                    actor.addAction(lastMoveAction);
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
//            case Input.Keys.LEFT:
//                actor.getActions().clear();
//                break;
//            case Input.Keys.RIGHT:
//                actor.getActions().clear();
//                break;
            case Input.Keys.SPACE:
                break;
            case Input.Keys.SHIFT_LEFT:
                break;
            default:
                actor.getActions().removeValue(lastMoveAction, false);
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

//        Viewport viewport = stage.getViewport();
//
//        float diffX = viewport.getScreenWidth() / viewport.getWorldWidth();
//        float diffY = viewport.getScreenHeight() / viewport.getWorldHeight();
//
//        Array<Actor> actors = stage.getActors();
//        Actor actor = null;
//        float translatedX = (screenX - viewport.getLeftGutterWidth()) / diffX;
//        float translatedY = Constants.SCREEN_HEIGHT - (screenY - viewport.getBottomGutterHeight
//                ()) / diffY; //inverted value, because scanning by Y goes from top to bottom,
//                             //but painting of objects by Y goes from bottom to top
//
//        Vector2 test = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
//        boolean overlap = false;
//        for (Actor a : actors) {
//            Rectangle ac = new Rectangle(a.getX(), a.getY(), a.getWidth(), a.getHeight());
//            overlap = ac.contains(test.x, test.y);
//            if (overlap) {
//                actor = a;
//                break;
//            }
//        }
//
//        if (overlap){
//            actor.setX(translatedX - actor.getWidth() / 2);
//            actor.setY(translatedY - actor.getHeight() / 2);
//        }
        Vector2 touchCoordinates = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
        Actor target = stage.hit(touchCoordinates.x, touchCoordinates.y, false);

        if (target != null) {
            float newX = touchCoordinates.x - (target.getWidth() / 2);
            float newY = touchCoordinates.y - (target.getHeight() / 2);
            target.setPosition(newX, newY);
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
