package dmme.kuvid.domain;

import dmme.kuvid.domain.Controllers.createHandler;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class KUVidGame extends Observable {
    private static KUVidGame instance = null;
    private static HashMap<Key, List<GameObject>> gameObjectMap = new HashMap<Key, List<GameObject>>();
    private final int range = 10;
    public boolean active = true;
    public boolean blendingMode;
    private Dimension screenSize;
    private int L;
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
    private int time;
    private Player p1;
    private Random rand = new Random();

    public KUVidGame() {
        this.shooter = new Shooter();
        this.blender = new Blender(this.creator, this.destroyer);
        this.p1 = Player.getInstance();
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.L = screenSize.height / 10;

        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.ATOM, AtomType.SIGMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.gameObjectMap.put(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA), new ArrayList<GameObject>());
    }

    public KUVidGame(int time, boolean active, boolean blendingMode) {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        publishPropertyEvent("time", this.time, time);
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

    public void runGame() { //main loop

        while (true) {
            if (this.p1.getHealth() <= 0) {
                break;
            }
            if (getTime() <= 0) {
                break;
            }

            if (this.active) {
                movementHandler.getInstance().run();
            }

            setTime(getTime() - 1);

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
        List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.randomMoleculeType()));
        GameObject molecule = list.get(this.rand.nextInt(list.size()));

        while (molecule.isActive()) {
            molecule = list.get(this.rand.nextInt(list.size()));
        }

        molecule.setPosition(new Position(this.rand.nextInt(this.getScreenSize().width), 0));
        // molecule.setDirection(direct);
        molecule.setActive(true);
    }
}
