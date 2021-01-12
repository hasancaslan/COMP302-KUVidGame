package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.Molecules.*;
import dmme.kuvid.domain.GameObjects.Powerup.*;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.*;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

public class DomainFactory extends Observable{
	 private static DomainFactory instance = null;
	 
	private DomainFactory() {
		
	}
	
	 public static DomainFactory getInstance() {
	        if (instance == null)
	            instance = new DomainFactory();

	        return instance;
	    }

    public void createAtom(AtomType type, int amount) {
        GameObject atom;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    atom = new AlphaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.ALPHA).add(atom);
                    publishPropertyEvent("alpha", null, atom);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    atom = new BetaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.BETA).add(atom);
                    publishPropertyEvent("beta", null, atom);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    atom = new SigmaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.SIGMA).add(atom);
                    publishPropertyEvent("sigma", null, atom);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    atom = new GamaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.GAMMA).add(atom);
                    publishPropertyEvent("gamma", null, atom);
                }
                break;
        }

        publishPropertyEvent("updateAtom",null,null);
    }

    public void createMolecule(MoleculeType type, int amount) {
        GameObject molecule;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    molecule = new AlphaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.ALPHA).add(molecule);
                    publishPropertyEvent("alphaMol", null, molecule);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    molecule = new BetaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.BETA).add(molecule);
                    publishPropertyEvent("betaMol", null, molecule);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new SigmaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.SIGMA).add(molecule);
                    publishPropertyEvent("sigmaMol", null, molecule);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new GamaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.GAMMA).add(molecule);
                    publishPropertyEvent("gammaMol", null, molecule);
                }
                break;
        }
    }

    public void createPowerup(PowerType type, int amount) {
        GameObject powerUp;
        switch (type) {
            case ALPHA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new AlphaPowerUp(new Position(0,0), new Position(0,0), false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.ALPHA_B).add(powerUp);
                    publishPropertyEvent("alphaPower", null, powerUp);
                }
                break;
            case BETA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new BetaPowerUp(new Position(0,0), new Position(0,0),false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.BETA_B).add(powerUp);
                    publishPropertyEvent("betaPower", null, powerUp);
                }
                break;
            case SIGMA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new SigmaPowerUp(new Position(0,0), new Position(0,0), false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.SIGMA_B).add(powerUp);
                    publishPropertyEvent("sigmaPower", null, powerUp);
                }
                break;
            case GAMMA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new GammaPowerUp(new Position(0,0), new Position(0,0), false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.GAMMA_B).add(powerUp);
                    publishPropertyEvent("gammaPower", null, powerUp);
                }
                break;
        }
    }

  public void createReactionBlocker(ReactionType type, int amount) {
        GameObject reactionBlocker;
        switch (type) {
            case ALPHA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new AlphaReactionBlocker(null, null,false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.ALPHA_R).add(reactionBlocker);
                    publishPropertyEvent("alphaBlocker", null, reactionBlocker);
                }
                break;
            case BETA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new BetaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.BETA_R).add(reactionBlocker);
                    publishPropertyEvent("betaBlocker", null, reactionBlocker);
                }
                break;
            case SIGMA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new SigmaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.SIGMA_R).add(reactionBlocker);
                    publishPropertyEvent("sigmaBlocker", null, reactionBlocker);
                }
                break;
            case GAMMA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new GammaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.GAMMA_R).add(reactionBlocker);
                    publishPropertyEvent("gammaBlocker", null, reactionBlocker);
                }
                break;
        }
    }
  
  public void addAtom(GameObject atom) {
	  AtomType type=(AtomType)atom.getSubType();
		  switch (type) {
		      case ALPHA:
		    	  if(atom.isActive()) {
		    		  if(atom.getDirection().getX()==0 && atom.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(atom);
		    			  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.ALPHA).add(atom);
		    		  }else {
		    			  KUVidGame.getShootedAtom().add((Atom) atom);
		    		  } 
		    	  }else {
		    		  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.ALPHA).add(atom);
		    	  }
		          publishPropertyEvent("alpha", null, atom);
		          break;
		      case BETA:
		    	  if(atom.isActive()) {
		    		  if(atom.getDirection().getX()==0 && atom.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(atom);
		    			  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.BETA).add(atom);
		    		  }else {
		    			  KUVidGame.getShootedAtom().add((Atom) atom);
		    		  } 
		    	  }else {
		    		  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.BETA).add(atom);
		    	  }
	              publishPropertyEvent("beta", null, atom);
		          break;
		      case SIGMA:
		    	  if(atom.isActive()) {
		    		  if(atom.getDirection().getX()==0 && atom.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(atom);
		    			  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.SIGMA).add(atom);
		    		  }else {
		    			  KUVidGame.getShootedAtom().add((Atom) atom);
		    		  } 
		    	  }else {
		    		  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.SIGMA).add(atom);
		    	  }
	              publishPropertyEvent("sigma", null, atom);
		          break;
		      case GAMMA:
		    	  if(atom.isActive()) {
		    		  if(atom.getDirection().getX()==0 && atom.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(atom);
		    			  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.GAMMA).add(atom);
		    		  }else {
		    			  KUVidGame.getShootedAtom().add((Atom) atom);
		    		  } 
		    	  }else {
		    		  GameObject.getGameObjectList(ObjectType.ATOM,AtomType.GAMMA).add(atom);
		    	  }
	              publishPropertyEvent("gamma", null, atom);
		          break;
		  }
		  
		  if(atom.isActive()) {
			  atom.setActive(true);
		  }
	  publishPropertyEvent("updateAtom",null,null);
  }
  
  public void addPowerUp(GameObject power) {
	  PowerType type=(PowerType)power.getSubType();
		  switch (type) {
		      case ALPHA_B:
		    	  if(power.isActive()) {
		    		  if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(power);
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.ALPHA_B).add(power);
		    		  }else if(power.getDirection().getY()<0){
		    			  KUVidGame.getShootedPower().add((PowerUp) power);
		    		  }else {
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.ALPHA_B).add(power);
		    		  }
		    	  }else {
                      if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
                          GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.ALPHA_B).add(power);
                      } else {
                          KUVidGame.getPowerArsenal().get(PowerType.ALPHA_B).add((PowerUp) power);
                      }
		    	  }
		          publishPropertyEvent("alphaPower", null, power);
		          break;
		      case BETA_B:
		    	  if(power.isActive()) {
		    		  if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(power);
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.BETA_B).add(power);
		    		  }else if(power.getDirection().getY()<0){
		    			  KUVidGame.getShootedPower().add((PowerUp) power);
		    		  }else {
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.BETA_B).add(power);
		    		  }
		    	  }else {
                      if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
                          GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.BETA_B).add(power);
                      } else {
                          KUVidGame.getPowerArsenal().get(PowerType.BETA_B).add((PowerUp) power);
                      }
		    	  }
		          publishPropertyEvent("betaPower", null, power);
		          break;
		      case SIGMA_B:
		    	  if(power.isActive()) {
		    		  if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(power);
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.SIGMA_B).add(power);
		    		  }else if(power.getDirection().getY()<0){
		    			  KUVidGame.getShootedPower().add((PowerUp) power);
		    		  }else {
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.SIGMA_B).add(power);
		    		  }
		    	  }else {
                      if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
                          GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.SIGMA_B).add(power);
                      } else {
                          KUVidGame.getPowerArsenal().get(PowerType.SIGMA_B).add((PowerUp) power);
                      }
		    	  }
		          publishPropertyEvent("sigmaPower", null, power);
		          break;
		      case GAMMA_B:
		    	  if(power.isActive()) {
		    		  if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
		    			  KUVidGame.getInstance().setCurrentAmmo(power);
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.GAMMA_B).add(power);
		    		  }else if(power.getDirection().getY()<0){
		    			  KUVidGame.getShootedPower().add((PowerUp) power);
		    		  }else {
		    			  GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.GAMMA_B).add(power);
		    		  }
		    	  }else {
                      if(power.getDirection().getX()==0 && power.getDirection().getY()==0) {
                          GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.GAMMA_B).add(power);
                      } else {
                          KUVidGame.getPowerArsenal().get(PowerType.GAMMA_B).add((PowerUp) power);
                      }
		    	  }
		          publishPropertyEvent("gammaPower", null, power);
		          break;
		  }
		  publishPropertyEvent("updatePower",null,null);
		  if(power.isActive()) {
			  power.setActive(true);
		  }
  }
  
  public void addMolecule(GameObject molecule) {
      MoleculeType type=(MoleculeType) molecule.getSubType();
      switch (type) {
          case ALPHA:
              GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.ALPHA).add(molecule);
              publishPropertyEvent("alphaMol", null, molecule);
              break;
          case BETA:
        	  GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.BETA).add(molecule);
              publishPropertyEvent("betaMol", null, molecule);
              break;
          case SIGMA:
        	  GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.SIGMA).add(molecule);
              publishPropertyEvent("sigmaMol", null, molecule);
              break;
          case GAMMA:
        	  GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.GAMMA).add(molecule);
              publishPropertyEvent("gammaMol", null, molecule);
              break;
      }
      if(molecule.isActive()) {
		  molecule.setActive(true);
	  }
      
  }
  
  public void addBlocker(GameObject blocker) {
	  ReactionType type=(ReactionType) blocker.getSubType();
      switch (type) {
          case ALPHA_R:
              GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.ALPHA_R).add(blocker);
              publishPropertyEvent("alphaBlocker", null, blocker);
              break;
          case BETA_R:
        	  GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.BETA_R).add(blocker);
              publishPropertyEvent("betaBlocker", null, blocker);
              break;
          case SIGMA_R:
        	  GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.SIGMA_R).add(blocker);
              publishPropertyEvent("sigmaBlocker", null, blocker);
              break;
          case GAMMA_R:
        	  GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.GAMMA_R).add(blocker);
              publishPropertyEvent("gammaBlocker", null, blocker);
              break;
      }
      if(blocker.isActive()) {
		  blocker.setActive(true);
	  }
      
  }

}
