package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.Molecules.*;
import dmme.kuvid.domain.GameObjects.Powerup.*;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.*;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.ui.Factory;
import dmme.kuvid.ui.GameFrame;

public class DomainFactory {

    public static void createAtom(AtomType type, int amount) {
        GameObject atom;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    atom = new AlphaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.ALPHA).add(atom);
                    Factory.createAlphaUI(atom);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    atom = new BetaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.BETA).add(atom);
                    Factory.createBetaUI(atom);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    atom = new SigmaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.SIGMA).add(atom);
                    Factory.createSigmaUI(atom);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    atom = new GammaAtom(new Position(0,0), new Position(0,0),false, ObjectType.ATOM);
                    GameObject.getGameObjectList(ObjectType.ATOM,AtomType.GAMMA).add(atom);
                    Factory.createGammaUI(atom);
                }
                break;
        }
        GameFrame.updateNumAtoms();
    }

    public static void createMolecule(MoleculeType type, int amount) {
        GameObject molecule;
        switch (type) {
            case ALPHA:
                for (int i = 0; i < amount; i++) {
                    molecule = new AlphaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.ALPHA).add(molecule);
                    Factory.createAlphaMoleculUI(molecule);
                }
                break;
            case BETA:
                for (int i = 0; i < amount; i++) {
                    molecule = new BetaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.BETA).add(molecule);
                    Factory.createBetaMoleculUI(molecule);
                }
                break;
            case SIGMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new SigmaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.SIGMA).add(molecule);
                    Factory.createSigmaMoleculUI(molecule);
                }
                break;
            case GAMMA:
                for (int i = 0; i < amount; i++) {
                    molecule = new GamaMolecule(new Position(0,0), new Position(0,0),false, ObjectType.MOLECULE);
                    GameObject.getGameObjectList(ObjectType.MOLECULE,MoleculeType.GAMMA).add(molecule);
                    Factory.createGammaMoleculUI(molecule);
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
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.ALPHA_B).add(powerUp);
                    Factory.createAlphaPowerUI(powerUp);
                }
                break;
            case BETA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new BetaPowerUp(null, null,false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.BETA_B).add(powerUp);
                    Factory.createBetaPowerUI(powerUp);
                }
                break;
            case SIGMA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new SigmaPowerUp(null, null, false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.SIGMA_B).add(powerUp);
                    Factory.createSigmaPowerUI(powerUp);
                }
                break;
            case GAMMA_B:
                for (int i = 0; i < amount; i++) {
                    powerUp = new GammaPowerUp(null, null, false, ObjectType.POWER_UP);
                    GameObject.getGameObjectList(ObjectType.POWER_UP,PowerType.GAMMA_B).add(powerUp);
                    Factory.createGammaPowerUI(powerUp);
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
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.ALPHA_R).add(reactionBlocker);
                    Factory.createAlphaBlockerUI(reactionBlocker);
                }
                break;
            case BETA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new BetaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.BETA_R).add(reactionBlocker);
                    Factory.createBetaBlockerUI(reactionBlocker);
                }
                break;
            case SIGMA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new SigmaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.SIGMA_R).add(reactionBlocker);
                    Factory.createSigmaBlockerUI(reactionBlocker);
                }
                break;
            case GAMMA_R:
                for (int i = 0; i < amount; i++) {
                    reactionBlocker = new GammaReactionBlocker(null, null, false, ObjectType.REACTION_BLOCKER);
                    GameObject.getGameObjectList(ObjectType.REACTION_BLOCKER,ReactionType.GAMMA_R).add(reactionBlocker);
                    Factory.createGammaBlockerUI(reactionBlocker);
                }
                break;
        }
    }

}
