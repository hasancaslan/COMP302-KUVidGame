package dmme.kuvid.domain.GameObjects;


import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.GameObjects.Powerup.PowerUp;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

public class Shooter extends Observable {
    private int position;
    private int angle;
    public AmmoType ammoType;
    public transient GameObject currentAmmo;

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
        if (this.currentAmmo != null) {
    		this.currentAmmo.setPosition(new Position(this.position,gameHeight-10*L));;
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
        if (this.currentAmmo != null) {
        	int x=this.position-(int)(10*L*Math.cos(angle));
        	int y=gameHeight-(int)(10*L*Math.sin(angle));
    		this.currentAmmo.setPosition(new Position(x,y));
    	}
    }

    /*
    REQUIRES: Domain factory have created some Atoms and current numOfAtoms is > 0.
    MODIFIES: Shooter's current atom is set to randomly picked atom, current atom becomes active,
    the current position of the shooter ammo assigned to selected atom
    EFFECTS: The previously selected but replaced ammo's (either power-up or atom)
     		 position and activeness properties are changed
     */
    public void pickAtom() {
    	if(KUVidGame.getInstance().getRemAtoms()>0) {
	    	int L=KUVidGame.getInstance().getL();
	    	int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
	    	if (this.currentAmmo != null) {
	    		this.currentAmmo.setActive(false);
	    	}
	        this.currentAmmo = movementHandler.getInstance().getRandomAtom();
	        double angle=Math.toRadians(this.getAngle());
	        int x=this.position-10*(int)(L*Math.cos(angle));
	    	int y=gameHeight-(int)(10*L*Math.sin(angle));
	        this.currentAmmo.setPosition(new Position(x,y));
	        this.currentAmmo.setActive(true);
    	}
    }
    
    public void pickPowerUp(PowerType type) {
    	if(KUVidGame.getPowerArsenal().get(type).size()>0) {
    		int L=KUVidGame.getInstance().getL();
	    	int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
	    	if (this.currentAmmo != null) {
	    		this.currentAmmo.setActive(false);
	    	}
	    	this.currentAmmo =KUVidGame.getPowerArsenal().get(type).get(KUVidGame.getPowerArsenal().get(type).size()-1);
	    	double angle=Math.toRadians(this.getAngle());
		    int x=this.position-10*(int)(L*Math.cos(angle));
		    int y=gameHeight-(int)(10*L*Math.sin(angle));
		    this.currentAmmo.setPosition(new Position(x,y));
		    this.currentAmmo.setDirection(null);
		    this.currentAmmo.setActive(true);
    	}
    	
    }

    //TODO refactoring for Shooter current'Atom' needed
	//TODO added
	public void pickShield(ShieldType type) {
		if(KUVidGame.getShieldArsenal().get(type)>0) {
			int L=KUVidGame.getInstance().getL();
			int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
			if (this.currentAmmo != null) {
				//this.currentAmmo.setActive(false);
			
			//Atom
			//ShieldDecorator temp = KUVidGame.getShieldArsenal().get(type).get(KUVidGame.getShieldArsenal().get(type).size() - 1);
			//this.currentAtom = temp.addShield();

			int curr = KUVidGame.getShieldArsenal().get(type);
			KUVidGame.getShieldArsenal().put(type, curr-1);
			
			

			switch(type) {
				case ETA:
					//TODO trying to take a shield with PowerUp ammo

					//System.out.println(((Atom)this.currentAtom).getStability());

					this.currentAmmo = new EtaShield((Atom)this.currentAmmo);
					//System.out.println(this.currentAtom instanceof Atom);
					//System.out.println("stability ->"+ ((Atom)this.currentAtom).getStability());
					//TODO currentAtom stability = ((Atom)this.currentAtom).getStability()) etc?
					//System.out.println("pace factor ->" +((Atom)this.currentAtom).paceFactor);

					//this.currentAtom = new EtaShield((Atom)this.currentAtom);
					//System.out.println(this.currentAtom instanceof Atom);
					//System.out.println("stability ->"+ ((Atom)this.currentAtom).getStability());
					//System.out.println("pace factor ->" +((Atom)this.currentAtom).paceFactor);

					break;
				case LOTA:
					this.currentAmmo = new LotaShield((Atom)this.currentAmmo);
					break;
				case THETA:
					this.currentAmmo = new ThetaShield((Atom)this.currentAmmo);
					break;
				case ZETA:
					this.currentAmmo = new ZetaShield((Atom)this.currentAmmo);
					break;
			}

			/*
			double angle=Math.toRadians(this.getAngle());
			int x=this.position-10*(int)(L*Math.cos(angle));
			int y=gameHeight-(int)(10*L*Math.sin(angle));
			this.currentAtom.setPosition(new Position(x,y));
			this.currentAtom.setDirection(null);
			this.currentAtom.setActive(true);

			 */
			}


		}
			

	}

    //TODO refactor the name of the method
    public void shootAmmo() {
    	if (this.currentAmmo != null) {
    		int L=KUVidGame.getInstance().getL();
    		int gameHeight=KUVidGame.getInstance().getPlayableArea().height;
    		double angle=Math.toRadians(this.getAngle()); 
    		double coeff=this.currentAmmo.paceFactor;
    		Position direction=new Position((int)(-L*Math.cos(angle)*coeff),(int)(-L*Math.sin(angle)*coeff));
    		this.currentAmmo.setDirection(direction);
    		
    		if(this.currentAmmo.getType().equals(ObjectType.POWER_UP)) {
    			KUVidGame.getShootedPower().add((PowerUp) this.currentAmmo);
    			KUVidGame.getPowerArsenal().get(this.currentAmmo.getSubType()).remove(this.currentAmmo);
    			publishPropertyEvent("updatePower",null,null);
    		}else {
    			KUVidGame.getShootedAtom().add((Atom) this.currentAmmo);
        		KUVidGame.getGameObjectMap().get(new Key(this.currentAmmo.getType(),this.currentAmmo.getSubType())).remove(this.currentAmmo);
        		publishPropertyEvent("updateAtom",null,null);
    		}
    		System.out.println(this.currentAmmo);
    		System.out.println("ammo dy:"+this.currentAmmo.getDirection().getY());
    		System.out.println("ammo pace factor:"+this.currentAmmo.paceFactor);
    		this.currentAmmo =null;
    		this.pickAtom();
    	}
    }

	@Override
	public String toString() {
		return "Shooter [" +
				"position=" + position +
				", angle=" + angle +
				", ammoType=" + ammoType +
				", currentAtom=" + currentAmmo +
				", propertyListenersMap=" + propertyListenersMap +
				']';
	}
}
