package dmme.kuvid.domain.GameObjects;

public class Player {
    private static Player instance = null;
    private int health = 100;
    private int point = 0;

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
        this.setPoint(this.getPoint() + increment);
    }

    public void decrementHealth(int decrement) {
        this.setHealth(this.getHealth() - decrement);
    }


}
