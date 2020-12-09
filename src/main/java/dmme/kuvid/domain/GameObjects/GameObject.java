package dmme.kuvid.domain.GameObjects;

import java.util.*;


import java.util.ArrayList;
import java.util.List;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.*;

public abstract class GameObject {
	

	Position Position;

	private String type;

	boolean active;
	
	public GameObject() {
		
	}
	
	public GameObject(Position position, boolean active) {
		super();
		Position = position;
		this.active = active;
		
	}

	
	public abstract void Collusion();
	

	public Position getPosition() {
		return Position;
	}

	public void setPosition(Position position) {
		Position = position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static List<GameObject> getGameObjectList(){
		return KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,AtomType.ALPHA));///MUST CHANGE
		
	}
	
	
	

	
}
