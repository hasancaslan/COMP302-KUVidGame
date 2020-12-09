package dmme.kuvid.domain.GameObjects;

import java.util.*;


import java.util.ArrayList;
import java.util.List;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.*;

public abstract class GameObject {


	Position position;
	Position direction;

	private ObjectType type;

	boolean active;

	public GameObject() {

	}

	public GameObject(Position position, Position direction, boolean active, ObjectType type) {
		super();
		this.position = position;
		this.direction = direction;
		this.active = active;
		this.type = type;

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

	public Position getDirection() {
		return direction;
	}

	public ObjectType getType() {
		return type;
	}

	public static List<GameObject> getGameObjectList(){
		return KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,AtomType.ALPHA));///MUST CHANGE

	}


	public void move() {

		if(this.isActive()) {
			int x1 = this.getPosition().getX();
			int y1 = this.getPosition().getY();
			int dx = this.getDirection().getX();
			int dy = this.getDirection().getY();

			int newX = x1 + dx;
			int newY = y1 + dy;

			if(newX > KUVidGame.getInstance().getN() * KUVidGame.getInstance().getL() || newX < 0) {//bouncing from the wall
				newX = x1 - dx;
				this.getDirection().setX(-dx);
			}
			Position nextPosition = new Position(newX, newY);
			this.setPosition(nextPosition);
		}
	}
}
