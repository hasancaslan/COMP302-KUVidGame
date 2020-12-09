package dmme.kuvid.domain;

import dmme.kuvid.domain.Controllers.*;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.lib.types.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class KUVidGame {
    private static KUVidGame instance = null;
    private final int L = 50;
    public Time time;
    public boolean active = true;
    public boolean blendingMode;
    private int numAtoms = 1;
    private int numMolecules = 1;
    private int numBlocker = 1;
    private int numPowerUp = 1;
    private GameLevel diff;
    private GameObject objects;
    private Shooter shooter;
    private Blender blender;
    private createHandler creator;
    private destroyHandler destroyer;
    private int N = 20;
    
    private int Time;
    private Player p1;
    private Random rand= new Random();
    private static HashMap<Key,List<GameObject>> gameObjectMap= new HashMap<Key,List<GameObject>>();

    public KUVidGame() {
        this.shooter = new Shooter();
        this.blender = new Blender(this.creator,this.destroyer);
        this.p1= new Player();
        
        
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM,AtomType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM,AtomType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM,AtomType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM,AtomType.SIGMA), new ArrayList<GameObject>());
		
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE,MoleculeType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE,MoleculeType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE,MoleculeType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE,MoleculeType.SIGMA), new ArrayList<GameObject>());
    }

    public KUVidGame(Time time, boolean active, boolean blendingMode) {
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
        shooter.rotateShooter(angleChange);
    }

    public void moveShooter(int displacement) {
        shooter.moveShooter(displacement);
    }

    public void selectAtom(AtomType type) {

    }

    public void selectPowerUp(PowerType type) {

    }

    public void useBlender(BlenderAction action,AtomType typeCreate,AtomType typeDestroy) {
    	this.blender.useBlender(action, typeCreate, typeDestroy);
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


    public int getN() {
        return N;
    }


    public void setN(int n) {
        this.N = n;
    }

    public void shooterStart() {
        this.shooter.setPosition(this.N * L / 2);
        this.shooter.setAngle(90);
    }
    
    public static HashMap<Key,List<GameObject>> getGameObjectMap(){
		return gameObjectMap;
	}
    
    public int getNumAtom(AtomType type) {
    	return KUVidGame.gameObjectMap.get(new Key(ObjectType.ATOM,type)).size();
    }
    
    public GameObject getRandomAtom() {
    	List<GameObject> list=KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,AtomType.randomAtomType()));
    	GameObject atom=list.get(this.rand.nextInt(list.size()));
    	
    	while(atom.isActive()) {
    		atom=list.get(this.rand.nextInt(list.size()));
    	}
    	
    	return atom;
    }
    
    public void runGame() {
    	
    	while (true){
    		if(this.p1.getHealth()<=0) {
    			break;
    		}
    		if(this.Time<=0) {
    			break;
    		}
    		
    		// Collision check
    		
    		if(this.active) {
    			//moveHandler()
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
    	return this.L;
    }
    
}
