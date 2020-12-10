package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

public class Shooter extends Observable {
    private int position;
    private int angle;
    public AmmoType ammoType;
    public GameObject currentAtom;

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

    public void moveShooter(int displacement) {
        setPosition(getPosition() + displacement);
    }

    public void rotateShooter(int angleChange) {
        setAngle(getAngle() + angleChange);
    }

    public void pickAtom() {
        this.currentAtom=KUVidGame.getInstance().getRandomAtom();
    }

    public void shootAtom() {
    	int L=KUVidGame.getInstance().getL();
    	int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
    	double angle=this.getAngle();
        this.currentAtom.setPosition(new Position(this.position,gameHeight-L));
        this.currentAtom.setDirection(new Position((int)Math.cos(angle),(int)Math.sin(angle)));
        this.currentAtom.setActive(true);
        this.pickAtom();
    }

}
