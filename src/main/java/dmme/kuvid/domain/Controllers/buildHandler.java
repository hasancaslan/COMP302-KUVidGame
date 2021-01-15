package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.ShieldType;

public class buildHandler {
	
	private KUVidGame Game;
	
	private static buildHandler instance = null;
	
    private buildHandler() {
    	Game =  KUVidGame.getInstance();
    }

    public static buildHandler getInstance() {
        if (instance == null)
            instance = new buildHandler();

        return instance;
    }
	 
	public void setNumAtoms(int atomNumber) {
		Game.setNumAtoms(atomNumber);
	}

	public void setNumMolecules(int moleculeNumber) {
		Game.setNumMolecules(moleculeNumber);
	}

	public void setNumBlocker(int reactionBlockerNumber) {
		Game.setNumBlocker(reactionBlockerNumber);
		
	}

	public void setNumPowerUp(int powerUpNumber) {
		Game.setNumPowerUp(powerUpNumber);
	}

	public void setL(int l) {
		Game.setL(l);
	}

	public void setDifficulty(String difficulty) {
		switch(difficulty) {
    	case "Easy":
    		Game.setDifficulty(1);
    		Game.setSleepTime(100);
    		break;
    	case "Medium":
    		Game.setDifficulty(2);
    		Game.setSleepTime(50);
    		break;
    	case "Hard":
    		Game.setDifficulty(4);
    		Game.setSleepTime(25);
    		break;
    	}		
	}
	

	public void setLinearity(boolean linear) {
		if(linear) {
			Game.setLinearity(1);
		}else{
			Game.setLinearity(0);
		}
    }

	public void setSpinning(boolean spinning) {
		Game.setSpinning(spinning);	
	}
	
	public void setShields(int num) {
		KUVidGame.getShieldArsenal().put(ShieldType.ETA, num);
        KUVidGame.getShieldArsenal().put(ShieldType.LOTA, num);
        KUVidGame.getShieldArsenal().put(ShieldType.THETA, num);
        KUVidGame.getShieldArsenal().put(ShieldType.ZETA, num);
	}
}



 