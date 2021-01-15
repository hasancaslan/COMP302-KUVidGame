package dmme.kuvid.domain.GameObjects;

import java.awt.Dimension;
import java.util.List;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

public abstract class GameObject extends Observable{

	protected int L = KUVidGame.getInstance().getL(); 
	protected Dimension gameField = KUVidGame.getInstance().getPlayableArea(); 
	protected Position position;
	protected Position direction;
	public double paceFactor=1.0; //if we need to change speed of particular objects at run time, unlike difficulty

	private ObjectType type;

	boolean active;

	public GameObject() {
		this.position=new Position(0,0);

	}

	public GameObject(Position position, Position direction, boolean active, ObjectType type) {
		super();
		this.position = position;
		this.direction = direction;
		this.active = active;
		this.type = type;

	}

	public abstract void Collusion();
	
	public abstract Enum getSubType();


	public Position getPosition() {
		return position;
	}

	public abstract void setPosition(Position position);

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		publishPropertyEvent("active", this.active, active);
		this.active = active;
	}

	public Position getDirection() {
		return direction;
	}

	public ObjectType getType() {
		return type;
	}
	
	public void setDirection(Position direct) {
		this.direction=direct;
	}

	public static List<GameObject> getGameObjectList(ObjectType type,Enum subType){
		return KUVidGame.getGameObjectMap().get(new Key(type,subType));

	}

	public void move() {
		// REQUIRES: This is initialized with position and direction
		// MODIFIES: This.Position and This.Direction
		// EFFECTS:  Updates position from this, so that new position = This.Position + This.Direction
		// 			 If position is out of playable area updates This.Direction so that This.Direction = -This.Direction
		if(this.isActive()) {
			int x1 = this.getPosition().getX();
			int y1 = this.getPosition().getY();
			int dx = this.getDirection().getX();
			int dy = this.getDirection().getY();
			
			if(dx!=0 | dy!=0) {
				int newX = x1 + dx;
				int newY = y1 + dy;
				

				if(newX > KUVidGame.getInstance().getPlayableArea().width || newX < 0) {
					newX = x1 - dx;
					this.getDirection().setX(-dx);
				}
				Position nextPosition = new Position(newX, newY);
				this.setPosition(nextPosition);
			}

		}
	}

	@Override
	public String toString() {
		return "GameObject [" +
				"L=" + L +
				", gameField=" + gameField +
				", position=" + position +
				", direction=" + direction +
				", type=" + type +
				", active=" + active +
				", propertyListenersMap=" + propertyListenersMap +
				']';
	}
}
