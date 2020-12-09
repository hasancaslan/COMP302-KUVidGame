package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.Molecules.*;
import dmme.kuvid.domain.GameObjects.Powerup.*;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.*;
import dmme.kuvid.lib.types.*;

public class createHandler {

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

    public static void createPowerup(PowerType type, int amount) {
        GameObject powerUp;
        switch (type) {
            case ALPHA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new AlphaPowerUp(null, null, false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList().add(powerUp);
                }
                break;
            case BETA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new BetaPowerUp(null, null,false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList().add(powerUp);
                }
                break;
            case SIGMA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new SigmaPowerUp(null, null, false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList().add(powerUp);
                }
                break;
            case GAMMA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new GammaPowerUp(null, null, false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList().add(powerUp);
                }
                break;
        }
    }

    public static void createReactionBlocker(ReactionType type, int amount) {
        GameObject reactionBlocker;
        switch (type) {
            case ALPHA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new AlphaReactionBlocker(null, null,false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList().add(reactionBlocker);
                }
                break;
            case BETA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new BetaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList().add(reactionBlocker);
                }
                break;
            case SIGMA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new SigmaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList().add(reactionBlocker);
                }
                break;
            case GAMMA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new GammaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList().add(reactionBlocker);
                }
                break;
        }
    }

}
