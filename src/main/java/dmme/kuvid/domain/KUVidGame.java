package dmme.kuvid.domain;

import dmme.kuvid.domain.Controllers.createHandler;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Molecules.Molecule;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class KUVidGame extends Observable implements Runnable {
    private static KUVidGame instance = null;
    private static HashMap<Key, List<GameObject>> gameObjectMap = new HashMap<Key, List<GameObject>>();
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
    private int difficulty=1;
    private int sleepTime=1000;
    private GameObject objects;
    private Shooter shooter;
    private Blender blender;
    private createHandler creator;
    private destroyHandler destroyer;
    private int time=600;
    private Player p1;
    private Random rand = new Random();
    private int throwMolecule;
    
    private int alphaNo=1;
    private int betaNo=1;
    private int gammaNo=1;
    private int sigmaNo=1;
    private int MOLNO=0;

    private KUVidGame() {
        this.shooter = new Shooter();
        this.blender = new Blender(this.creator, this.destroyer);
        this.p1 = Player.getInstance();
        this.screenSize = new Dimension(1280,640);
        		//Toolkit.getDefaultToolkit().getScreenSize();
        this.playableArea = new Dimension(this.screenSize.width*7/10,this.screenSize.height);
        this.L=Math.floorDiv(screenSize.height,10);
        this.creator=new createHandler();
        

        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.SIGMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA), new ArrayList<GameObject>());
        
        
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
    		sleepTime= 1000;
    		break;
    	case "Medium":
    		this.difficulty = 2;
    		sleepTime= 500;
    		break;
    	case "Hard":
    		this.difficulty = 4;
    		sleepTime= 250;
    		break;
    	}
    }

    public boolean getLinearity() {
        return linearity;
    }

    public void setLinearity(boolean linearity) {
    	this.linearity = linearity;
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

    public GameObject getRandomAtom() {
        List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.randomAtomType()));
        GameObject atom = list.get(this.rand.nextInt(list.size()));

        while (atom.isActive()) {
            atom = list.get(this.rand.nextInt(list.size()));
        }

        return atom;
    }

    public void runGame(){ //main loop
    	
    	int num=Math.floorDiv(this.numAtoms,4);
        int numMol=Math.floorDiv(this.numMolecules, 4);
        this.throwMolecule=numMol*4;
        this.MOLNO=numMol;
        
        createHandler.createAtom(AtomType.ALPHA,num);
        createHandler.createAtom(AtomType.BETA,num);
        createHandler.createAtom(AtomType.GAMMA,num);
        createHandler.createAtom(AtomType.SIGMA,num);
        
        
        createHandler.createMolecule(MoleculeType.ALPHA, numMol);
        createHandler.createMolecule(MoleculeType.BETA, numMol);
        createHandler.createMolecule(MoleculeType.GAMMA, numMol);
        createHandler.createMolecule(MoleculeType.SIGMA, numMol);
        

        while (true) {
            if (this.p1.getHealth() <= 0) {
                break;
            }
            if (getTime() <= 0) {
                break;
            }

            if(this.active) {
            	for(int i = this.difficulty; i>0 ;i--) {
                	try {
        				Thread.sleep(sleepTime);
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}
                	if(this.throwMolecule>0) {
                		throwMolecule();
                	}
            	}
            	movementHandler.getInstance().run();
            	setTime(getTime() - 1);
            }else {
            	try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

            

        }
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
}
