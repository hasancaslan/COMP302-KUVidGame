package dmme.kuvid.domain;

import dmme.kuvid.lib.types.AmmoType;
import dmme.kuvid.utils.observer.Observable;

public class Shooter extends Observable {
    private double position;
    private int angle;
    public AmmoType ammoType;

    public Shooter(double position, int angle, AmmoType ammoType) {
        this.position = position;
        this.angle = angle;
        this.ammoType = ammoType;
    }

    public Shooter() {
        this(500, 90, AmmoType.ATOM);
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        publishPropertyEvent("position", this.position, position);
        this.position = position;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        publishPropertyEvent("angle", this.angle, angle);
        this.angle = angle;
    }

    public AmmoType getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
    }

    public void moveShooter(double displacement) {
        setPosition(getPosition() + displacement);
    }

    public void rotateShooter(int angleChange) {
        setAngle(getAngle() + angleChange);
    }
}
