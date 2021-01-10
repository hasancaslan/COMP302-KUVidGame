package dmme.kuvid.domain.GameObjects;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;

public class GameObjectTest2{
	Position position = new Position(0,0);
	Position direction = new Position(10,10);
	ObjectType type = ObjectType.ATOM;	
	GameObject atom;
	
	@Test
	public void testList() {
		List<GameObject> GameObjectList = GameObject.getGameObjectList(ObjectType.ATOM , AtomType.ALPHA);
		assert(true);
	}
	

	@Test
	public void testActiveness() {
		atom = new AlphaAtom(position, direction, true, type);
		assertTrue(atom.isActive());
		atom.setActive(false);
		assertFalse(atom.isActive());
	}
	
	@Test
	public void testPosition() {
		atom = new AlphaAtom(position, direction, true, type);
		assertTrue(atom.getPosition().getX() == 0 && atom.getPosition().getY() == 0);
		atom.setPosition(new Position(100,100));
		assertTrue(atom.getPosition().getX() == 100 && atom.getPosition().getY() == 100);
	}
	
	
	@Test
	public void testDirection() {
		atom = new AlphaAtom(position, direction, true, type);
		atom.setDirection(new Position(0,0));
		assertTrue(atom.getDirection().getX() == 0 && atom.getDirection().getY() == 0);
		atom.setDirection(new Position(100,100));
		assertTrue(atom.getDirection().getX() == 100 && atom.getDirection().getY() == 100);
	}
	
	@Test
	public void testMove() {
		atom = new AlphaAtom(position, direction, true, type);
		atom.setPosition(new Position(0,0));
		atom.setDirection(new Position(100,100));
		atom.move();
		assertTrue(atom.getPosition().getX() == 100 * (1) && atom.getPosition().getY() == 100 * (1));
		atom.move();
		assertTrue(atom.getPosition().getX() == 100 * (2) && atom.getPosition().getY() == 100 * (2));
		atom.setDirection(new Position(-100,-100));
		atom.move();
		assertTrue(atom.getPosition().getX() == 100 * (1) && atom.getPosition().getY() == 100 * (1));
		atom.move();
		assertTrue(atom.getPosition().getX() == 100 * (0) && atom.getPosition().getY() == 100 * (0));
		atom.move();
		assertFalse(atom.getDirection().getX() == -100);
		assertFalse(atom.getPosition().getX() == -100);
		assertTrue(atom.getDirection().getX() == 100);
		assertTrue(atom.getPosition().getX() == 100);
	}

}
