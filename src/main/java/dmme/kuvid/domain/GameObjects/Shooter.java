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
    	int L=KUVidGame.getInstance().getL();
    	int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
    	int gameWidth=KUVidGame.getInstance().getPlayableArea().width;
    	int newPosition = getPosition() + displacement;
    	if(newPosition > gameWidth) newPosition = gameWidth;
    	if(newPosition < -5*L) newPosition = -5*L;
        setPosition(newPosition);
        if (this.currentAtom!= null) {
    		this.currentAtom.setPosition(new Position(this.position,gameHeight-10*L));;
    	}
        
    }

    public void rotateShooter(int angleChange) {
    	int L=KUVidGame.getInstance().getL();
    	int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
    	double angle=Math.toRadians(this.getAngle());
    	int newAngle = getAngle() + angleChange;
    	if(newAngle > 180) newAngle = 180;
    	if(newAngle < 0) newAngle = 0;
        setAngle(newAngle);
        if (this.currentAtom!= null) {
        	int x=this.position-(int)(10*L*Math.cos(angle));
        	int y=gameHeight-(int)(10*L*Math.sin(angle));
    		this.currentAtom.setPosition(new Position(x,y));
    	}
    }

    public void pickAtom() {
    	int L=KUVidGame.getInstance().getL();
    	int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
    	if (this.currentAtom!= null) {
    		this.currentAtom.setActive(false);
    	}
        this.currentAtom=KUVidGame.getInstance().getRandomAtom();
        double angle=Math.toRadians(this.getAngle());
        int x=this.position-10*(int)(L*Math.cos(angle));
    	int y=gameHeight-(int)(10*L*Math.sin(angle));
        this.currentAtom.setPosition(new Position(x,y));
        this.currentAtom.setActive(true);
    }

    public void shootAtom() {
    	if (this.currentAtom!= null) {
    		int L=KUVidGame.getInstance().getL();
    		int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
    		double angle=Math.toRadians(this.getAngle()); 
    		Position direction=new Position((int)(-L*Math.cos(angle)),(int)(-L*Math.sin(angle)));
    		this.currentAtom.setDirection(direction);
    		this.currentAtom=null;
    		this.pickAtom();
    	}
    }

}
