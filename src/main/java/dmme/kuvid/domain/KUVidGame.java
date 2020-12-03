package dmme.kuvid.domain;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.shooter.*;
import dmme.kuvid.lib.types.*;

import java.sql.Time;

public class KUVidGame {
    public Time time;
    public boolean active=true;
    public boolean blendingMode;
    private int numAtoms=1;
    private int numMolecules=1;
    private int numBlocker=1;
    private int numPowerUp=1;
    private GameLevel diff;
    private GameObject objects;
    private Shooter shooter;
    private ShooterHandler shootControl;
    private int N=20;
    private final int L=50;
    
 //
    
    
    public KUVidGame() {
    	this.shooter=new Shooter();
    	this.shootControl=new ShooterHandler(this.shooter);
    	
    	
    }
    private Shooter shooter;


    public KUVidGame(Time time, boolean active, boolean blendingMode) {
        this.time = time;
        this.active = active;
        this.blendingMode = blendingMode;
        this.shooter = new Shooter();
    }
    

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBlendingMode() {
        return blendingMode;
    }

    public void setBlendingMode(boolean blendingMode) {
        this.blendingMode = blendingMode;
    }

    public void aimShooter(int angleChange) {
        shooter.rotateShooter(angleChange);
    }

    public void moveShooter(double displacement) {
        shooter.moveShooter(displacement);
    }

    public void selectAtom(AtomType type) {

    }

    public void selectPowerUp(PowerType type) {

    }

    public void useBlender(boolean mode, AtomType type, int number) {

    }

	public int getNumPowerUp() {
		return numPowerUp;
	}

	public void setNumPowerUp(int numPowerUp) {
		this.numPowerUp = numPowerUp;
	}

	public int getNumBlocker() {
		return numBlocker;
	}

	public void setNumBlocker(int numBlocker) {
		this.numBlocker = numBlocker;
	}

	public int getNumMolecules() {
		return numMolecules;
	}

	public void setNumMolecules(int numMolecules) {
		this.numMolecules = numMolecules;
	}

	public int getNumAtoms() {
		return numAtoms;
	}

	public void setNumAtoms(int numAtoms) {
		this.numAtoms = numAtoms;
	}
	
	public Shooter getShooter() {
		return this.shooter;
		
	}


	public int getN() {
		return N;
	}


	public void setN(int n) {
		this.N = n;
	}
	
	public void shooterStart() {
		this.shooter.setPosition(this.N*L/2);
		this.shooter.setAngle(90);
	}
}
