package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.AlphaMolecule;
import dmme.kuvid.domain.GameObjects.Molecules.BetaMolecule;
import dmme.kuvid.domain.GameObjects.Molecules.GamaMolecule;
import dmme.kuvid.domain.GameObjects.Molecules.SigmaMolecule;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.PowerType;

public class createHandler {

    //public static void createObjectForBlender(GameObject object, int amount);

    public static void createAtom(AtomType type, int amount) {
        GameObject atom;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    atom = new AlphaAtom(null, null,false, ObjectType.ATOM);
                    GameObject.getGameObjectList().add(atom);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    atom = new BetaAtom(null, null,false, ObjectType.ATOM);
                    GameObject.getGameObjectList().add(atom);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    atom = new SigmaAtom(null, null,false, ObjectType.ATOM);
                    GameObject.getGameObjectList().add(atom);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    atom = new GamaAtom(null, null,false, ObjectType.ATOM);
                    GameObject.getGameObjectList().add(atom);
                }
                break;
        }
    }

    public static void createMolecule(MoleculeType type, int amount) {
        GameObject molecule;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    molecule = new AlphaMolecule(null, null,false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    molecule = new BetaMolecule(null, null,false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new SigmaMolecule(null, null,false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new GamaMolecule(null, null,false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
        }
    }

    /*
    public static void createPowerup(PowerType type, int amount) {
        GameObject molecule;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    molecule = new AlphaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    molecule = new BetaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new SigmaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new GamaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
        }
    }

    public static void createReactionBlocker(PowerType type, int amount) {
        GameObject molecule;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    molecule = new AlphaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    molecule = new BetaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new SigmaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new GamaMolecule(null, false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList().add(molecule);
                }
                break;
        }
    }
     */

}
