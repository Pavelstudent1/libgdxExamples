package utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import actors.Ground;
import actors.Player;
import utils.Const.ACTOR_STATE;

public class Collisions implements ContactListener {
	
	private Player p;
	private Ground g;

	public Collisions(Player p, Ground g) {
		this.p = p;
		this.g = g;
	}

	@Override
	public void beginContact(Contact contact) {
		
		short mask = contact.getFixtureA().getFilterData().maskBits;
		if (mask == 3){
			p.setGrounded();
		}
	}

	@Override
	public void endContact(Contact contact) {
		p.state = ACTOR_STATE.IN_AIR;
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
