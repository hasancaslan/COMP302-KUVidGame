package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.lib.types.ObjectType;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
	
	private static List<GameObject> gameObjectList = new ArrayList<>();

	private Position position;



	private Position direction;

	private ObjectType type;

	boolean active;
	
	public GameObject(Position position, Position direction, boolean active, ObjectType type) {
		super();
		this.position = position;
		this.direction = direction;
		this.active = active;
		this.type = type;
	}

	public static List<GameObject> getGameObjectList() {
		return gameObjectList;
	}

	
	public abstract void Collusion();
	

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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

	public Position getDirection() {
		return direction;
	}

	public void setDirection(Position direction) {
		this.direction = direction;
	}

}
