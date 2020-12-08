package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.lib.types.AmmoType;
import dmme.kuvid.utils.observer.Observable;

public class Shooter extends Observable {
    private int position;
    private int angle;
    public AmmoType ammoType;

    public Shooter(int position, int angle, AmmoType ammoType) {
        this.position = position;
        this.angle = angle;
        this.ammoType = ammoType;
    }

    public Shooter() {
        this(500, 90, null);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        publishPropertyEvent("position", this.position, position);
        this.position = position;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {	//NOTE: 0 ve 180 olacak ama her 10derecede 1 image lazým
    	if(angle<10) angle=10;
    	if(angle>170) angle=170;
        publishPropertyEvent("angle", this.angle, angle);
        this.angle = angle;
    }

    public AmmoType getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
    }

    public void moveShooter(int displacement) {
        setPosition(getPosition() + displacement);
    }

    public void rotateShooter(int angleChange) {
        setAngle(getAngle() + angleChange);
    }
}
