package dmme.kuvid.domain.game;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowMoleculeTest {
    GameObject molecule;

    @Test
    public void testList() {
        KUVidGame.getInstance().setNumMolecules(10);
        DomainFactory.createMolecule(MoleculeType.ALPHA, 10);
        List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA));
        assertNotNull(list, "GameObject list is null.");
    }

    @Test
    public void checkMoleculeNum() {
        KUVidGame.getInstance().setNumMolecules(4);
        DomainFactory.createMolecule(MoleculeType.ALPHA, 1);
        DomainFactory.createMolecule(MoleculeType.BETA, 1);
        DomainFactory.createMolecule(MoleculeType.GAMMA, 1);
        DomainFactory.createMolecule(MoleculeType.SIGMA, 1);
//        movementHandler.getInstance().throwMolecule();

        List<GameObject> alphaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA));
        List<GameObject> betaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.BETA));
        List<GameObject> gammaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA));
        List<GameObject> sigmaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA));

        assertEquals(1, alphaList.size(), "List Size don't match.");
        assertEquals(1, betaList.size(), "List Size don't match.");
        assertEquals(1, gammaList.size(), "List Size don't match.");
        assertEquals(1, sigmaList.size(), "List Size don't match.");

    }
}
