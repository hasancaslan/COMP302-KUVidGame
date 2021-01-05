package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.utils.observer.Observable;

public class Player extends Observable {
    private static Player instance = null;
    private int health = 100;
    private double point = 0;

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
        this.setHealth(this.getHealth() - decrement);
    }


}
