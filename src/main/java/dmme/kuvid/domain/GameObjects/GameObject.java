package dmme.kuvid.domain.GameObjects;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

import dmme.kuvid.lib.types.ObjectType;

public abstract class GameObject {
	
	private static List<GameObject> gameObjectList = new ArrayList<>();

	Position Position;
	Position Direction;

	private ObjectType type;

	boolean active;
	
	public GameObject(Position position, Position direction, boolean active) {
		super();
		Position = position;
		Direction = direction;
		this.active = active;
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

	public Position getDirection() {
		return Direction;
	}

	public void setDirection(Position direction) {
		Direction = direction;
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
	
	
	

	
}
