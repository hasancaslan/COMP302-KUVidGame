package dmme.kuvid.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Molecules.Molecule;
import dmme.kuvid.domain.GameObjects.Powerup.PowerUp;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.ReactionBlocker;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.ui.GameFrame;
import dmme.kuvid.utils.observer.Observable;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class KUVidGame extends Observable implements Runnable {
    private static KUVidGame instance = null;
    private static HashMap<Key, List<GameObject>> gameObjectMap = new HashMap<Key, List<GameObject>>();
    private static List<GameObject> shootedAtom = new ArrayList<>();
    private static List<GameObject> shootedPower= new ArrayList<>();
    private static HashMap<PowerType, List<PowerUp>> powerArsenal = new HashMap<PowerType, List<PowerUp>>();
    public boolean active = true;
    public boolean blendingMode;
    private Dimension screenSize;
    private Dimension playableArea;
    private int L;
    private int numAtoms = 1;
    private int numMolecules = 1;
    private int numBlocker = 1;
    private int numPowerUp = 1;
    private final int range = 10;

    private boolean linearity;
    private boolean spinning;
    private int difficulty=1;
    private int sleepTime=100;
    private GameObject objects;
    private Shooter shooter;
    private Blender blender;
    private DomainFactory creator;
    private destroyHandler destroyer;
    private int time=60;//600;
    private Player p1;
    private Random rand = new Random();
    private int throwMolecule;
    private int throwBlocker;
    private int throwPower;
    
    private int alphaPowerNo=1;
    private int betaPowerNo=1;
    private int gammaPowerNo=1;
    private int sigmaPowerNo=1;
    private int POWERNO=0;
    private int alphaBlockNo=1;
    private int betaBlockNo=1;
    private int gammaBlockNo=1;
    private int sigmaBlockNo=1;
    private int BLOCKNO=0;
    private int alphaNo=1;
    private int betaNo=1;
    private int gammaNo=1;
    private int sigmaNo=1;
    private int MOLNO=0;
    private int count = 0;
    private int RemAtoms=0;

    private KUVidGame() {
        this.shooter = new Shooter();
        this.blender = new Blender(this.creator, this.destroyer);
        this.p1 = Player.getInstance();
        this.screenSize = new Dimension(1280,640);
        		//Toolkit.getDefaultToolkit().getScreenSize();
        this.playableArea = new Dimension(this.screenSize.width*7/10,this.screenSize.height);
        this.L=Math.floorDiv(screenSize.height,10);
        this.creator=new DomainFactory();
        

        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.SIGMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.REACTION_BLOCKER, ReactionType.ALPHA_R), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.REACTION_BLOCKER, ReactionType.BETA_R), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.REACTION_BLOCKER, ReactionType.GAMMA_R), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.REACTION_BLOCKER, ReactionType.SIGMA_R), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.POWER_UP, PowerType.ALPHA_B), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.POWER_UP, PowerType.BETA_B), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.POWER_UP, PowerType.GAMMA_B), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.POWER_UP, PowerType.SIGMA_B), new ArrayList<GameObject>());
        KUVidGame.powerArsenal.put(PowerType.ALPHA_B, new ArrayList<PowerUp>());
        KUVidGame.powerArsenal.put(PowerType.BETA_B, new ArrayList<PowerUp>());
        KUVidGame.powerArsenal.put(PowerType.GAMMA_B, new ArrayList<PowerUp>());
        KUVidGame.powerArsenal.put(PowerType.SIGMA_B, new ArrayList<PowerUp>());
        
    }

    private KUVidGame(int time, boolean active, boolean blendingMode) {
        this.time = time;
        this.active = active;
        this.blendingMode = blendingMode;
        this.shooter = new Shooter();
    }

    public static KUVidGame getInstance() {
        if (instance == null)
            instance = new KUVidGame();

        return instance;
    }

    public static HashMap<Key, List<GameObject>> getGameObjectMap() {
        return gameObjectMap;
    }
    
    public static HashMap<PowerType, List<PowerUp>> getPowerArsenal(){
		return powerArsenal;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }
    
    public Dimension getPlayableArea() {
        return this.playableArea;
    }

    public void setPlayableArea(Dimension size) {
        this.playableArea = size;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        publishPropertyEvent("time", this.time, time);
        this.time = time;
    }
    
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
    	switch(difficulty) {
    	case "Easy":
    		this.difficulty = 1;
    		sleepTime= 100;
    		break;
    	case "Medium":
    		this.difficulty = 2;
    		sleepTime= 50;
    		break;
    	case "Hard":
    		this.difficulty = 4;
    		sleepTime= 25;
    		break;
    	}
    }

    public int getLinearity() {
    	if(linearity) return 1;
        return 0;
    }

    public void setLinearity(boolean linearity) {
    	this.linearity = linearity;
    }
    
    public boolean getSpinning() {
    	return this.spinning;
    }

    public void setSpinning(boolean spinning) {
    	this.spinning=spinning;
    }

    public boolean isActive() {
        return active;
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBlendingMode() {
        return blendingMode;
    }

    public void setBlendingMode(boolean blendingMode) {
        this.blendingMode = blendingMode;
    }

    public void aimShooter(int angleChange) {
    	if(this.active) {
    		shooter.rotateShooter(angleChange);
    	}
    }

    public void moveShooter(int displacement) {
    	if(this.active) {
    		shooter.moveShooter(displacement);
    	}
    }

    public void selectAtom() {
    	if(this.active) {
    		shooter.pickAtom();
    	}
    }

    public void selectPowerUp(PowerType type) {
    	this.shooter.pickPowerUp(type);
    }

    public int getRange() {
        return range;
    }

    public void useBlender(BlenderAction action, AtomType typeCreate, AtomType typeDestroy) {
        this.blender.useBlender(action, typeCreate, typeDestroy);
    }

    public int getNumPowerUp() {
        return numPowerUp;
    }

    public void setNumPowerUp(int numPowerUp) {
        this.numPowerUp = numPowerUp;
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

    public void setNumAtoms(int numAtoms) {
        this.numAtoms = numAtoms;
    }

    public Shooter getShooter() {
        return this.shooter;
    }

    public void shooterStart() {
        this.shooter.setPosition(this.getScreenSize().width / 2);
        this.shooter.setAngle(90);
    }

    public int getNumAtom(AtomType type) {
        return KUVidGame.gameObjectMap.get(new Key(ObjectType.ATOM, type)).size();
    }
    
    public int getNumMol(MoleculeType type) {
        return KUVidGame.gameObjectMap.get(new Key(ObjectType.MOLECULE, type)).size();
    }
    
    public int getNumPower(PowerType type) {
    	return KUVidGame.powerArsenal.get(type).size();
    }

    public GameObject getRandomAtom() {
        List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.randomAtomType()));
        
        while(list.size()==0) {
        	list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.randomAtomType()));
        }
        
        GameObject atom = list.get(this.rand.nextInt(list.size()));
        
        while (atom.isActive()) {
            atom = list.get(this.rand.nextInt(list.size()));
        }

        return atom;
    }

    public void runGame(){ //main loop
    	
    	int num=(int) Math.ceil((this.numAtoms/4.0));
        int numMol=(int) Math.ceil((this.numMolecules/4.0));
        int numBlock=(int) Math.ceil((this.numBlocker/4.0));
        int numPower=(int) Math.ceil((this.numPowerUp/4.0));
        this.throwMolecule=numMol*4;
        this.MOLNO=numMol;
        this.throwBlocker=numBlock*4;
        this.BLOCKNO=numBlock;
        this.throwPower=numPower*4;
        this.POWERNO=numPower;
        
        DomainFactory.createAtom(AtomType.ALPHA,num);
        DomainFactory.createAtom(AtomType.BETA,num);
        DomainFactory.createAtom(AtomType.GAMMA,num);
        DomainFactory.createAtom(AtomType.SIGMA,num);
        
        
        DomainFactory.createMolecule(MoleculeType.ALPHA, numMol);
        DomainFactory.createMolecule(MoleculeType.BETA, numMol);
        DomainFactory.createMolecule(MoleculeType.GAMMA, numMol);
        DomainFactory.createMolecule(MoleculeType.SIGMA, numMol);
        
        DomainFactory.createReactionBlocker(ReactionType.ALPHA_R, numBlock);
        DomainFactory.createReactionBlocker(ReactionType.BETA_R, numBlock);
        DomainFactory.createReactionBlocker(ReactionType.SIGMA_R, numBlock);
        DomainFactory.createReactionBlocker(ReactionType.GAMMA_R, numBlock);
        
        DomainFactory.createPowerup(PowerType.ALPHA_B, numPower);
        DomainFactory.createPowerup(PowerType.BETA_B, numPower);
        DomainFactory.createPowerup(PowerType.SIGMA_B, numPower);
        DomainFactory.createPowerup(PowerType.GAMMA_B, numPower);
        
        int select=0;

        String toBeLoaded = null;
        while (true) {
            if (this.p1.getHealth() <= 0) {
                break;
            }
            if (getTime() <= 0) {
                break;
            }
            if(this.getRemMolecules()==0) {
            	break;
            }
            

            if(this.active) {
            	movementHandler.getInstance().run();
            	count++;
            	for(int i = this.difficulty; i>0 ;i--) {
                	try {
        				Thread.sleep(sleepTime);
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}
                	if(count == 10) {
                		if(select==0 && this.throwMolecule>0) {
                			throwMolecule();
                			select++;
                		}else if(select==1 && this.throwBlocker>0){
                			throwBlocker();
                			select++;
                		}else {
                			throwPower();
                			select=0;
                		}
                	}
            	}
            	if(count == 10) {
            		count = 0;
            		setTime(getTime() - 1);
            	}

                Type type = new TypeToken<HashMap<Key, List<?>>>(){}.getType();
            	toBeLoaded = save();

            } else {
                load(toBeLoaded);
            	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

            

        }
        
        GameFrame.finishedGame();
    }

    public void pauseGame() {
        this.setActive(false);
    }

    public void resumeGame() {
        this.setActive(true);
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public void throwMolecule() {
    	
    	
    	MoleculeType t=MoleculeType.randomMoleculeType();
    	List<GameObject> list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE,t));
    	
    	while(list.size()==0) {
    		t=MoleculeType.randomMoleculeType();
    		list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE,t));
    	}
    	
    	Molecule molecule=null;
    	while(molecule==null) {
    		
	    	if(t.equals(MoleculeType.ALPHA) && this.alphaNo<=this.MOLNO) {
	    		molecule=(Molecule)list.get(this.MOLNO-this.alphaNo);
	    		this.alphaNo++;
	    	}else if(t.equals(MoleculeType.BETA) && this.betaNo<=this.MOLNO) {
	    		molecule=(Molecule)list.get(this.MOLNO-this.betaNo);
	    		this.betaNo++;
	    	}else if(t.equals(MoleculeType.GAMMA) && this.gammaNo<=this.MOLNO) {
	    		molecule=(Molecule)list.get(this.MOLNO-this.gammaNo);
	    		this.gammaNo++;
	    	}else if(t.equals(MoleculeType.SIGMA) && this.sigmaNo<=this.MOLNO) {
	    		molecule=(Molecule)list.get(this.MOLNO-this.sigmaNo);
	    		this.sigmaNo++;
	    	}
        
        	t=MoleculeType.randomMoleculeType();
    		list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE,t));
    		
        }
        
        molecule.setPosition(new Position(this.rand.nextInt(this.playableArea.width - 30*L) + 10 * L ,0));
        molecule.setActive(true);
        this.throwMolecule--;
        
       
    }
    
    public void throwBlocker() {
    	ReactionType t=ReactionType.randomReactionType();
    	List<GameObject> list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.REACTION_BLOCKER,t));
    	
    	while(list.size()==0) {
    		t=ReactionType.randomReactionType();
    		list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.REACTION_BLOCKER,t));
    	}
    	
    	ReactionBlocker blocker=null;
    	while(blocker==null) {
    		
	    	if(t.equals(ReactionType.ALPHA_R) && this.alphaBlockNo<=this.BLOCKNO) {
	    		blocker=(ReactionBlocker)list.get(this.BLOCKNO-this.alphaBlockNo);
	    		this.alphaBlockNo++;
	    	}else if(t.equals(ReactionType.BETA_R) && this.betaBlockNo<=this.BLOCKNO) {
	    		blocker=(ReactionBlocker)list.get(this.BLOCKNO-this.betaBlockNo);
	    		this.betaBlockNo++;
	    	}else if(t.equals(ReactionType.GAMMA_R) && this.gammaBlockNo<=this.BLOCKNO) {
	    		blocker=(ReactionBlocker)list.get(this.BLOCKNO-this.gammaBlockNo);
	    		this.gammaBlockNo++;
	    	}else if(t.equals(ReactionType.SIGMA_R) && this.sigmaBlockNo<=this.BLOCKNO) {
	    		blocker=(ReactionBlocker)list.get(this.BLOCKNO-this.sigmaBlockNo);
	    		this.sigmaBlockNo++;
	    	}
        
        	t=ReactionType.randomReactionType();
    		list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.REACTION_BLOCKER,t));
    		
        }
        
        blocker.setPosition(new Position(this.rand.nextInt(this.playableArea.width - 30*L) + 10 * L ,0));
        blocker.setActive(true);
        this.throwBlocker--;
    	
    }
    
    public void throwPower() {
    	PowerType t=PowerType.randomPowerType();
    	List<GameObject> list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.POWER_UP,t));
    	
    	while(list.size()==0) {
    		t=PowerType.randomPowerType();
    		list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.POWER_UP,t));
    	}
    	
    	PowerUp power=null;
    	while(power==null) {
    		
	    	if(t.equals(PowerType.ALPHA_B) && this.alphaPowerNo<=this.POWERNO) {
	    		power=(PowerUp)list.get(this.POWERNO-this.alphaPowerNo);
	    		this.alphaPowerNo++;
	    	}else if(t.equals(PowerType.BETA_B) && this.betaPowerNo<=this.POWERNO) {
	    		power=(PowerUp)list.get(this.POWERNO-this.betaPowerNo);
	    		this.betaPowerNo++;
	    	}else if(t.equals(PowerType.GAMMA_B) && this.gammaPowerNo<=this.POWERNO) {
	    		power=(PowerUp)list.get(this.POWERNO-this.gammaPowerNo);
	    		this.gammaPowerNo++;
	    	}else if(t.equals(PowerType.SIGMA_B) && this.sigmaPowerNo<=this.POWERNO) {
	    		power=(PowerUp)list.get(this.POWERNO-this.sigmaPowerNo);
	    		this.sigmaPowerNo++;
	    	}
        
        	t=PowerType.randomPowerType();
    		list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.POWER_UP,t));
    		
        }
        
    	power.setPosition(new Position(this.rand.nextInt(this.playableArea.width - 30*L) + 10 * L ,0));
    	power.setDirection(new Position(0,L));
    	power.setActive(true);
        this.throwPower--;
    	
    }
    
    public void shoot() {
    	if(this.active) {
    		this.shooter.shootAtom();
    	}
    }
    

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.runGame();
	}
	
	private void prettyPrint(List<GameObject> list) {
		System.out.print("[");
		for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).isActive());
            System.out.print(" , ");
        }
		System.out.print("]");
		System.out.println("");
	}

	public static List<GameObject> getShootedAtom() {
		return shootedAtom;
	}

	public int getRemAtoms() {
		return this.getNumAtom(AtomType.ALPHA)+this.getNumAtom(AtomType.BETA)+this.getNumAtom(AtomType.SIGMA)+this.getNumAtom(AtomType.GAMMA);
	}
	
	public int getRemMolecules() {
		return this.getNumMol(MoleculeType.ALPHA)+this.getNumMol(MoleculeType.BETA)+this.getNumMol(MoleculeType.SIGMA)+this.getNumMol(MoleculeType.GAMMA);
	}
	
	public int getScore() {
		return this.p1.getPoint();
	}

	public static List<GameObject> getShootedPower() {
		return shootedPower;
	}

    private String save() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(getGameObjectMap());
        //System.out.println("SERIALIZED");
        //System.out.println("Company Info: " + jsonString);
        return "";
    }


    private void load(String toBeLoaded) {
    }
}
