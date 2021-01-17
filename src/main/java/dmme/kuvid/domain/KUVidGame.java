package dmme.kuvid.domain;

import dmme.kuvid.Application;
import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.GameObjects.Blender;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.GameObjects.Powerup.PowerUp;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.domain.database.SaveLoadDatabase;
import dmme.kuvid.domain.database.SaveLoadFile;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.utils.observer.Observable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KUVidGame extends Observable implements Runnable {
    private static KUVidGame instance = null;
    private static HashMap<Key, List<GameObject>> gameObjectMap = new HashMap<Key, List<GameObject>>();
    private static List<Atom> shootedAtom = new ArrayList<>();
    private static List<PowerUp> shootedPower = new ArrayList<>();
    private static HashMap<PowerType, List<PowerUp>> powerArsenal = new HashMap<PowerType, List<PowerUp>>();
    private static HashMap<ShieldType, Integer> shieldArsenal = new HashMap<ShieldType, Integer>();

    private static SaveLoadFile saveLoadFile = new SaveLoadFile();
    private static SaveLoadDatabase saveLoadDatabase = new SaveLoadDatabase();
    private final int range = 10;
    public boolean active = true;
    public boolean blendingMode;
    private Dimension screenSize;
    private Dimension playableArea;
    private int L;
    private int numAtoms = 1;
    private int numMolecules = 1;
    private int numBlocker = 1;
    private int numPowerUp = 1;
    private int linearity = 1;
    private boolean spinning;

    private int difficulty = 1;
    private int sleepTime = 100;

    private GameObject objects;
    private Shooter shooter;
    private Blender blender;
    private DomainFactory creator;
    private destroyHandler destroyer;
    private int time = 600;

    private int throwMolecule;
    private int throwBlocker;
    private int throwPower;

    private int count = 0;

    private boolean quit = false;
    private SaveType loadMode = SaveType.NONE;

    private KUVidGame() {
        this.shooter = new Shooter();
        this.blender = new Blender(this.creator, this.destroyer);
        this.screenSize = new Dimension(1280, 640);
        //Toolkit.getDefaultToolkit().getScreenSize();
        this.playableArea = new Dimension(this.screenSize.width * 7 / 10, this.screenSize.height);
        this.L = Math.floorDiv(screenSize.height, 10);
        this.creator = DomainFactory.getInstance();

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

    public static HashMap<PowerType, List<PowerUp>> getPowerArsenal() {
        return powerArsenal;
    }

    //TODO added this
    public static HashMap<ShieldType, Integer> getShieldArsenal() {
        return shieldArsenal;
    }

    public static List<Atom> getShootedAtom() {
        return shootedAtom;
    }

    public static List<PowerUp> getShootedPower() {
        return shootedPower;
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

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getLinearity() {
        return this.linearity;
    }

    public void setLinearity(int linearity) {
        this.linearity = linearity;
    }

    public int getSleepTime() {
        return this.sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public boolean getSpinning() {
        return this.spinning;
    }

    public void setSpinning(boolean spinning) {
        this.spinning = spinning;
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
        if (this.active) shooter.rotateShooter(angleChange);
    }

    public void moveShooter(int displacement) {
        if (this.active) shooter.moveShooter(displacement);
    }

    public void selectAtom() {
        if (this.active) shooter.pickAtom();
    }

    public void selectPowerUp(PowerType type) {
        this.shooter.pickPowerUp(type);
    }

    //TODO added this
    public void selectShield(ShieldType type) {
        this.shooter.pickShield(type);
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

    public void shoot() {
        if (this.active) this.shooter.shootAmmo();
    }

    public Shooter getShooter() {
        return this.shooter;
    }

    public void setShooter(Shooter shot) {
        this.shooter = shot;
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

    public int getRemAtoms() {
        return this.getNumAtom(AtomType.ALPHA) + this.getNumAtom(AtomType.BETA) + this.getNumAtom(AtomType.SIGMA) + this.getNumAtom(AtomType.GAMMA);
    }

    public int getRemMolecules() {
        return this.getNumMol(MoleculeType.ALPHA) + this.getNumMol(MoleculeType.BETA) + this.getNumMol(MoleculeType.SIGMA) + this.getNumMol(MoleculeType.GAMMA);
    }

    public double getScore() {
        return Player.getInstance().getPoint();
    }

    public Blender getBlender() {
        return this.blender;
    }

    public void runGame() {
        if (this.loadMode == SaveType.NONE) {
            publishPropertyEvent("load", null, false);
            publishPropertyEvent("updateShield", null, null);
            int num = (int) Math.ceil((this.numAtoms / 4.0));
            int numMol = (int) Math.ceil((this.numMolecules / 4.0));
            int numBlock = (int) Math.ceil((this.numBlocker / 4.0));
            int numPower = (int) Math.ceil((this.numPowerUp / 4.0));

            this.throwMolecule = numMol * 4;
            this.throwPower = numPower * 4;
            this.throwBlocker = numBlock * 4;

            DomainFactory.getInstance().createAtom(AtomType.ALPHA, num);
            DomainFactory.getInstance().createAtom(AtomType.BETA, num);
            DomainFactory.getInstance().createAtom(AtomType.GAMMA, num);
            DomainFactory.getInstance().createAtom(AtomType.SIGMA, num);

            DomainFactory.getInstance().createMolecule(MoleculeType.ALPHA, numMol);
            DomainFactory.getInstance().createMolecule(MoleculeType.BETA, numMol);
            DomainFactory.getInstance().createMolecule(MoleculeType.GAMMA, numMol);
            DomainFactory.getInstance().createMolecule(MoleculeType.SIGMA, numMol);

            DomainFactory.getInstance().createReactionBlocker(ReactionType.ALPHA_R, numBlock);
            DomainFactory.getInstance().createReactionBlocker(ReactionType.BETA_R, numBlock);
            DomainFactory.getInstance().createReactionBlocker(ReactionType.SIGMA_R, numBlock);
            DomainFactory.getInstance().createReactionBlocker(ReactionType.GAMMA_R, numBlock);

            DomainFactory.getInstance().createPowerup(PowerType.ALPHA_B, numPower);
            DomainFactory.getInstance().createPowerup(PowerType.BETA_B, numPower);
            DomainFactory.getInstance().createPowerup(PowerType.SIGMA_B, numPower);
            DomainFactory.getInstance().createPowerup(PowerType.GAMMA_B, numPower);
        } else {
            if (this.loadMode == SaveType.DATABASE)
                saveLoadDatabase.loadInit();
            else if (this.loadMode == SaveType.LOCAL)
                saveLoadFile.loadInit();

            this.L = Player.getInstance().getL();
            this.load(this.loadMode);

            this.setNumMolecules(this.getRemMolecules());
            this.throwMolecule = movementHandler.getInstance().numMolToThrow();
            this.throwBlocker = movementHandler.getInstance().numBlockToThrow();
            this.throwPower = movementHandler.getInstance().numPowerToThrow();
            publishPropertyEvent("load", null, true);
            this.time = Player.getInstance().getTime();
            this.difficulty = Player.getInstance().getDifficulty();
            this.linearity = Player.getInstance().getLinearity();
            this.spinning = Player.getInstance().isSpin();
            KUVidGame.shieldArsenal = Player.getInstance().getRemainingShields();
            publishPropertyEvent("updateShield", null, null);
        }

        int select = 0;
        Application.getInstance().getLogger().i("Game runing ...");
        while (true) {
            if (Player.getInstance().getHealth() <= 0) {
                break;
            }
            if (getTime() <= 0) {
                break;
            }
            if (this.getRemMolecules() == 0) {
                break;
            }

            if (this.quit) {
                break;
            }

            if (this.active) {
                movementHandler.getInstance().run();
                count++;
                for (int i = this.difficulty; i > 0; i--) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count == 10) {
                        if (select == 0 && this.throwMolecule > 0) {
                            movementHandler.getInstance().throwMolecule();
                            this.throwMolecule--;
                            select++;
                        } else if (select == 1 && this.throwBlocker > 0) {
                            movementHandler.getInstance().throwBlocker();
                            this.throwBlocker--;
                            select++;
                        } else if (this.throwPower > 0) {
                            movementHandler.getInstance().throwPower();
                            this.throwPower--;
                            select = 0;
                        }
                    }
                }
                if (count == 10) {
                    count = 0;
                    setTime(getTime() - 1);
                    publishPropertyEvent("tick", getTime() + 1, getTime());
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        publishPropertyEvent("finishGame", null, null);
        Application.getInstance().getLogger().i("Game Finished!");
    }

    @Override
    public void run() {
        this.runGame();
    }

    private void prettyPrint(List<GameObject> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).isActive());
            System.out.print(" , ");
        }
        System.out.print("]");
        System.out.println("");
    }

    public SaveLoadFile getSaveLoadFile() {
        return KUVidGame.saveLoadFile;
    }

    public void save(SaveType type) {
    	Application.getInstance().getLogger().i("Saving game to "+type+" ...");
        this.snapshotPlayer();
        if (type == SaveType.DATABASE) {
            saveLoadDatabase.saveGame();
        }else if (type == SaveType.LOCAL) {
            saveLoadFile.saveGame();
        }
    }

    public void load(SaveType type) {
        if (type == SaveType.DATABASE) {
            saveLoadDatabase.loadGame();
        }else if (type == SaveType.LOCAL) {
            saveLoadFile.loadGame();
        }
    }

    public void setQuit(boolean quitting) {
        this.quit = quitting;
    }

    public void setLoadMode(SaveType mode) {
        this.loadMode = mode;
    }

    public void setCurrentAmmo(GameObject ammo) {
        this.shooter.currentAmmo = ammo;
    }

    public int getShieldNum(ShieldType type) {
        return KUVidGame.shieldArsenal.get(type);
    }

    private void snapshotPlayer() {
        Player.getInstance().setTime(this.time);
        Player.getInstance().setDifficulty(this.difficulty);
        Player.getInstance().setL(this.L);
        Player.getInstance().setSpin(this.spinning);
        Player.getInstance().setLinearity(this.linearity);
        Player.getInstance().setRemainingShields(KUVidGame.getShieldArsenal());
    }

}
