package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.lib.types.ObjectType;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
	
	private static List<GameObject> gameObjectList = new ArrayList<>();

	Position Position;

	private ObjectType type;

	boolean active;
	
	public GameObject(Position position, boolean active, ObjectType type) {
		super();
		Position = position;
		this.active = active;
		this.type = type;
	}

	public static List<GameObject> getGameObjectList() {
		return gameObjectList;
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

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

}
