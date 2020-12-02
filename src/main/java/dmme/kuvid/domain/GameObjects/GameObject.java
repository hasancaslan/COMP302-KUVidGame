package dmme.kuvid.domain.GameObjects;


public abstract class GameObject {
	
	Position Position;
	boolean active;
	
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
	
	
	

	
}
