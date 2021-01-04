package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Collusion.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.domain.GameObjects.Powerup.PowerUp;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.ReactionType;
import dmme.kuvid.ui.GameFrame;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class movementHandler {

    private static movementHandler instance = null;
    
    private List<GameObject> garbage=new ArrayList<GameObject>();
    private List<GameObject> collidedMol=new ArrayList<GameObject>();
    private List<GameObject> collidedAtom=new ArrayList<GameObject>();
    private List<GameObject> collidedBlocker=new ArrayList<GameObject>();
    private List<GameObject> collidedPower=new ArrayList<GameObject>();
    private List<GameObject> eliminateBlocker=new ArrayList<GameObject>();

    private movementHandler() {}

    public static movementHandler getInstance() {
        if (instance == null)
            instance = new movementHandler();

        return instance;
    }

    final int range = KUVidGame.getInstance().getRange();

    public void search() {
        HashMap<Key,List<GameObject>> map= KUVidGame.getGameObjectMap();
        
       int L = KUVidGame.getInstance().getL();
       
       for(GameObject atom : KUVidGame.getShootedAtom()) {
    	   int x1 = atom.getPosition().getX();
           int y1 = atom.getPosition().getY();
    	   for(MoleculeType Mtype: MoleculeType.values()) {
    		   List<GameObject> MolList = map.get(new Key(ObjectType.MOLECULE,Mtype));
    		   List<GameObject> BlockList = map.get(new Key(ObjectType.REACTION_BLOCKER,ReactionType.valueOf(Mtype.toString()+"_R")));
    		   for(GameObject mol : MolList) {
    			   if (!mol.isActive()) continue;
    			   int x2 = mol.getPosition().getX();
    	           int y2 = mol.getPosition().getY();
    	           if (Math.abs(x1 - x2) < 10*L && Math.abs(y1 - y2) < 10*L) { 
                      
                      this.collidedMol.add(mol);
                      this.collidedAtom.add(atom);
                      
                      boolean added=false;
                      for(GameObject block : BlockList) {
           			   if (!block.isActive()) continue;
           			   int xb = block.getPosition().getX();
           	           int yb = block.getPosition().getY();
           	           if (Math.abs(x1 - xb) < 15*L && Math.abs(y1 - yb) < 15*L) { 
                             
                             this.collidedBlocker.add(block);
                             added=true;
           	           }
                      }
                      if(!added) {
                    	  this.collidedBlocker.add(null);
                      }
    		   }
    	   }
    	   }
       }
       
       
       for(int i=0; i<this.collidedMol.size();i++) {
    	   
    	   if(this.collidedBlocker.get(i)==null) {
    		   new AtomMoleculeCollision(this.collidedAtom.get(i), this.collidedMol.get(i),false);
    	   }else {
    		   if((this.collidedMol.get(i).getSubType().toString()+"_R").equals(this.collidedBlocker.get(i).getSubType().toString())) {
    			   new AtomMoleculeCollision(this.collidedAtom.get(i), this.collidedMol.get(i),true);
    			   System.out.println("BLOCKED");
    			   destroyHandler.destroyObject(this.collidedBlocker.get(i));
    		   }else {
    			   new AtomMoleculeCollision(this.collidedAtom.get(i), this.collidedMol.get(i),false);
    			   System.out.println("TYPE MISS");
    		   }
    	   }
    	   
       }
       this.collidedAtom.clear();
       this.collidedMol.clear();
       this.collidedBlocker.clear();
       
       for(GameObject power : KUVidGame.getShootedPower()) {
    	   int x1 = power.getPosition().getX();
           int y1 = power.getPosition().getY();
    	   for(MoleculeType Mtype: MoleculeType.values()) {
    		   List<GameObject> BlockList = map.get(new Key(ObjectType.REACTION_BLOCKER,ReactionType.valueOf(Mtype.toString()+"_R")));
    		   for(GameObject block : BlockList) {
    			   if (!block.isActive()) continue;
    			   int xb = block.getPosition().getX();
       	           int yb = block.getPosition().getY();
    	           if (Math.abs(x1 - xb) < 10*L && Math.abs(y1 - yb) < 10*L) {  
                      this.collidedPower.add(power);
                      this.eliminateBlocker.add(block);
    		   }
    	      }
    	   }
       }
       
       for(int i=0; i<this.eliminateBlocker.size();i++) {
    	   new PowerReactionCollision(this.collidedPower.get(i),this.eliminateBlocker.get(i));
       }
       this.collidedPower.clear();
       this.eliminateBlocker.clear();
    }

    public void move() {
    	int L=KUVidGame.getInstance().getL();
    	HashMap<Key,List<GameObject>> map= KUVidGame.getGameObjectMap();
        for (Key k: map.keySet()) {
        	 List<GameObject> gameObjectList = map.get(k);
	        for (GameObject gameObject : gameObjectList) {
	        	if(gameObject.isActive()) {
	        		gameObject.move();
	        		
	        		if(gameObject.getType().equals(ObjectType.REACTION_BLOCKER)) {
	        			int shooterPosition= KUVidGame.getInstance().getShooter().getPosition();
	        			int objectPosition= gameObject.getPosition().getX();
	        			if((Math.abs(shooterPosition-objectPosition)<5*L)&& gameObject.isActive()&&gameObject.getPosition().getY()>(KUVidGame.getInstance().getPlayableArea().height-20*L)) {
	        				this.garbage.add(gameObject);
	        			}else if(gameObject.getPosition().getY()>(KUVidGame.getInstance().getPlayableArea().height-10*L) && gameObject.isActive()) {
			            	this.garbage.add(gameObject);
			        	}
	        		}else if(gameObject.getType().equals(ObjectType.POWER_UP)) {
	        			int shooterPosition= KUVidGame.getInstance().getShooter().getPosition();
	        			int objectPosition= gameObject.getPosition().getX();
	        			if((Math.abs(shooterPosition-objectPosition)<5*L)&& gameObject.isActive()&&gameObject.getPosition().getY()>(KUVidGame.getInstance().getPlayableArea().height-20*L)&&(!KUVidGame.getPowerArsenal().get(gameObject.getSubType()).contains(gameObject))) {
	        				this.garbage.add(gameObject);
	        				KUVidGame.getPowerArsenal().get(gameObject.getSubType()).add((PowerUp) gameObject);
	        				GameFrame.updateNumPower();
	        			}else if(gameObject.getPosition().getY()>(KUVidGame.getInstance().getPlayableArea().height-10*L) && gameObject.isActive()) {
			            	this.garbage.add(gameObject);
			        	}
	        		}else {
	        			if(gameObject.getPosition().getY()>KUVidGame.getInstance().getPlayableArea().height && gameObject.isActive()) {
			            	this.garbage.add(gameObject);
			        	}
	        		}
	        	}
	        }
        }
        
        for(GameObject atom : KUVidGame.getShootedAtom()) {
        	atom.move();
        	if(atom.getPosition().getY()<0) {
        		this.garbage.add(atom);
        	}
        }
        
        for(GameObject power : KUVidGame.getShootedPower()) {
        	power.move();
        	if(power.getPosition().getY()<0) {
        		this.garbage.add(power);
        	}
        }
        
        for(GameObject gameObject: garbage) {
        	if(gameObject.getType().equals(ObjectType.REACTION_BLOCKER)) {
        		new ReactionSurfaceCollision(gameObject);
        	}else {
        		destroyHandler.destroyObject(gameObject);
        	}
        }
        garbage.clear();
        this.search();
    }

    public void run() {
            search();
            move();
    }
}