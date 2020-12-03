package dmme.kuvid.domain;

import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.PowerType;

import java.sql.Time;

public class KUVidGame {
    public Time time;
    public boolean active;
    public boolean blendingMode;
    private int numAtoms=1;
    private int numMolecules=1;
    private int numBlocker=1;
    private int numPowerUp=1;
    
 //
    
    
    public KUVidGame() {
    	
    }


    public KUVidGame(Time time, boolean active, boolean blendingMode) {
        this.time = time;
        this.active = active;
        this.blendingMode = blendingMode;
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

    public void aimShooter(int angle) {

    }

    public void moveShooter(double displacement) {

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
}
