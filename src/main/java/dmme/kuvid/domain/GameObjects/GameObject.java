package dmme.kuvid.domain.GameObjects;


import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

	private static List<GameObject> gameObjectList = new ArrayList<>();

	Position Position;

	private String type;

	boolean active;
	
	public GameObject(Position position, boolean active) {
		super();
		Position = position;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

	
}
