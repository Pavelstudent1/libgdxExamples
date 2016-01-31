package utils;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import actors.Player;

public class InputKeyboard implements InputProcessor{
	
	private Player player;

	public InputKeyboard(Player player) {
		this.player = player;
	}
	
	@Override
	public boolean keyDown(int keycode) {

		switch (keycode) {
		case Keys.UP:
			player.jump();
			break;

		default:
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
