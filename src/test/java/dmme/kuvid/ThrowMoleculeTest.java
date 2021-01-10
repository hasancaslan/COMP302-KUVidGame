package dmme.kuvid;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.Molecule;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowMoleculeTest {
    @BeforeEach
    void clear() throws Exception {
        KUVidGame.getInstance();
        KUVidGame.getInstance().setNumMolecules(0);
        KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA), new ArrayList<GameObject>());
        KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), new ArrayList<GameObject>());
        KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA), new ArrayList<GameObject>());
        KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA), new ArrayList<GameObject>());
    }

    @Test
    void testAllEmpty() {
        assertEquals(0, KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA)).size());
        assertEquals(0, KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.BETA)).size());
        assertEquals(0, KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA)).size());
        assertEquals(0, KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA)).size());
    }

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

        List<GameObject> alphaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA));
        List<GameObject> betaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.BETA));
        List<GameObject> gammaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA));
        List<GameObject> sigmaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA));

        assertEquals(1, alphaList.size(), "List Size don't match.");
        assertEquals(1, betaList.size(), "List Size don't match.");
        assertEquals(1, gammaList.size(), "List Size don't match.");
        assertEquals(1, sigmaList.size(), "List Size don't match.");
    }

    @Test
    public void checkMoleculeIsActive() {
        KUVidGame.getInstance().setNumMolecules(4);
        KUVidGame.getInstance().setPlayableArea(new Dimension(1000, 1000));
        KUVidGame.getInstance().setL(10);

        DomainFactory.getInstance().createMolecule(MoleculeType.ALPHA, 1);
        DomainFactory.getInstance().createMolecule(MoleculeType.BETA, 1);
        DomainFactory.getInstance().createMolecule(MoleculeType.GAMMA, 1);
        DomainFactory.getInstance().createMolecule(MoleculeType.SIGMA, 1);

        movementHandler.getInstance().throwMolecule();

        List<GameObject> alphaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA));
        List<GameObject> betaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.BETA));
        List<GameObject> gammaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA));
        List<GameObject> sigmaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA));

        List<GameObject> merged = Stream.concat(Stream.concat(gammaList.stream(), sigmaList.stream())
                .collect(Collectors.toList()).stream(), Stream.concat(alphaList.stream(), betaList.stream())
                .collect(Collectors.toList()).stream())
                .collect(Collectors.toList());

        boolean check = false;
        for (GameObject molecule : merged) {
            if (molecule.isActive()) {
                check = true;
                break;
            }
        }

        assertTrue(check);
    }

    public void checkMoleculeIsNotActive() {
        KUVidGame.getInstance().setNumMolecules(4);
        KUVidGame.getInstance().setPlayableArea(new Dimension(1000, 1000));
        KUVidGame.getInstance().setL(10);

        DomainFactory.getInstance().createMolecule(MoleculeType.ALPHA, 1);
        DomainFactory.getInstance().createMolecule(MoleculeType.BETA, 1);
        DomainFactory.getInstance().createMolecule(MoleculeType.GAMMA, 1);
        DomainFactory.getInstance().createMolecule(MoleculeType.SIGMA, 1);

        List<GameObject> alphaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA));
        List<GameObject> betaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.BETA));
        List<GameObject> gammaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.GAMMA));
        List<GameObject> sigmaList = KUVidGame.getGameObjectMap().get(new Key(ObjectType.MOLECULE, MoleculeType.SIGMA));

        List<GameObject> merged = Stream.concat(Stream.concat(gammaList.stream(), sigmaList.stream())
                .collect(Collectors.toList()).stream(), Stream.concat(alphaList.stream(), betaList.stream())
                .collect(Collectors.toList()).stream())
                .collect(Collectors.toList());

        boolean check = false;
        for (GameObject molecule : merged) {
            if (molecule.isActive()) {
                check = true;
                break;
            }
        }

        assertFalse(check);
    }
}
