package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.utils.observer.Observable;

public class Player extends Observable{
	private int health=100;
	private int point=0;

	private static Player instance = null;
	
	private Player() {
	}

	public static Player getInstance() {
		if (instance == null)
			instance = new Player();

		return instance;
	}

	public int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}

	public int getPoint() {
		return point;
	}

	private void setPoint(int point) {
		this.point = point;
	}
	
	public void incrementPoint(int increment) {
		 publishPropertyEvent("point", this.point, this.point+1);
		this.setPoint(this.getPoint()+increment);
	}
	
	public void decrementHealth(int decrement) {
		this.setHealth(this.getHealth()-decrement);
	}
	
	
}
