package dmme.kuvid.domain.GameObjects;

public class Position {
    int x=0;
    int y=0;

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position [" +
                "x=" + x +
                ", y=" + y +
                ']';
    }
}
