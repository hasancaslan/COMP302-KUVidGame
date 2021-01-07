package dmme.kuvid.domain.game;

import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.AlphaMolecule;
import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.ui.BuildingWindow;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowMoleculeTest {
    Position position = new Position(0, 0);
    Position direction = new Position(10, 10);
    MoleculeType moleculeType = MoleculeType.ALPHA;
    ObjectType objectType = ObjectType.ATOM;
    GameObject molecule;

    @Test
    public void testList() {
        BuildingWindow buildingWindow = new BuildingWindow();
        buildingWindow.createGame();
        List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(objectType, moleculeType));
        assertNotNull(list, "Game Object list is null");
    }

    @Test
    public void checkMoleculeNum() {
        movementHandler.getInstance().throwMolecule();
        assertTrue(molecule.getPosition().getX() == 0 && molecule.getPosition().getY() == 0);
    }

    @Test
    public void testPosition() {
        molecule = new AlphaMolecule(position, direction, true, objectType);
        assertTrue(molecule.getPosition().getX() == 0 && molecule.getPosition().getY() == 0);
        molecule.setPosition(new Position(100, 100));
        assertTrue(molecule.getPosition().getX() == 100 && molecule.getPosition().getY() == 100);
    }

    @Test
    public void testActiveness() {
        molecule = new AlphaAtom(position, direction, true, objectType);
        assertTrue(molecule.isActive());
        molecule.setActive(false);
        assertFalse(molecule.isActive());
    }

    @Test
    public void testMove() {
        molecule = new AlphaMolecule(position, direction, true, objectType);
        molecule.setPosition(new Position(0, 0));
        molecule.setDirection(new Position(100, 100));
        molecule.move();
        assertTrue(molecule.getPosition().getX() == 100 && molecule.getPosition().getY() == 100);
        molecule.move();
        assertTrue(molecule.getPosition().getX() == 100 * (2) && molecule.getPosition().getY() == 100 * (2));
        molecule.setDirection(new Position(-100, -100));
        molecule.move();
        assertTrue(molecule.getPosition().getX() == 100 && molecule.getPosition().getY() == 100);
        molecule.move();
        assertTrue(molecule.getPosition().getX() == 0 && molecule.getPosition().getY() == 0);
        molecule.move();
        assertNotEquals(molecule.getDirection().getX(), -100);
        assertNotEquals(molecule.getPosition().getX(), -100);
        assertEquals(100, molecule.getDirection().getX());
        assertEquals(100, molecule.getPosition().getX());
    }

}
