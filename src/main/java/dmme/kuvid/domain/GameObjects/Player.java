package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.utils.observer.Observable;

public class Player extends Observable {
    private static Player instance = null;
    private int health = 100;
    private double point = 0;
    private int time;
    private int difficulty;
    private int L;
    private boolean spin;
    private int linearity; 

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
        publishPropertyEvent("health", this.health, health);
        this.health = health;
    }

    public double getPoint() {
        return point;
    }

    private void setPoint(double point) {
        publishPropertyEvent("point", this.point, point);
        this.point = point;
    }

    public void incrementPoint(double increment) {
        this.setPoint(this.getPoint() + increment);
    }

    public void decrementHealth(int decrement) {
    	if(this.getHealth()>decrement) {
    		this.setHealth(this.getHealth() - decrement);
    	}else {
    		this.setHealth(0);
    	}
    }

    @Override
    public String toString() {
        return "Player [" +
                "health=" + health +
                ", point=" + point +
                ", propertyListenersMap=" + propertyListenersMap +
                ']';
    }

	public int getTime() {
		publishPropertyEvent("point", null, this.point);
		publishPropertyEvent("health", null, this.health);
		publishPropertyEvent("time", null, this.time);
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public static void setInstance(Player p1) {
		Player.instance=p1;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getL() {
		return L;
	}

	public void setL(int l) {
		L = l;
	}

	public boolean isSpin() {
		return spin;
	}

	public void setSpin(boolean spin) {
		this.spin = spin;
	}

	public int getLinearity() {
		return linearity;
	}

	public void setLinearity(int linearity) {
		this.linearity = linearity;
	}
}
