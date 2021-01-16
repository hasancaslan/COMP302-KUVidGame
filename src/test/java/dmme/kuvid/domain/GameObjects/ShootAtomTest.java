package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.domain.GameObjects.Atoms.BetaAtom;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.ui.GameFrame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShootAtomTest {
	
	@BeforeEach
	void clearList() {
		KUVidGame.getShootedAtom().clear();
	}
	
	@Test 
	void testCurrentAtom() {
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
		KUVidGame.getInstance().getShooter().pickAtom();
		KUVidGame.getInstance().getShooter().shootAmmo();
		assertEquals(KUVidGame.getInstance().getShooter().currentAmmo, null);
	}
	
	@Test
	void testListChange() {
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1); 
		KUVidGame.getInstance().getShooter().pickAtom();
		KUVidGame.getInstance().getShooter().shootAmmo();
		assertEquals(KUVidGame.getShootedAtom().size(), 1);
	}
	
	@Test 
	void testDirection() {
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
		KUVidGame.getInstance().getShooter().pickAtom();
		KUVidGame.getInstance().getShooter().shootAmmo();
		int L=KUVidGame.getInstance().getL();
		double angle=Math.toRadians(KUVidGame.getInstance().getShooter().getAngle()); 
		Position directest=new Position((int)(-L*Math.cos(angle)),(int)(-L*Math.sin(angle)));
		assertEquals(KUVidGame.getShootedAtom().get(0).direction.getX(), directest.getX());
		assertEquals(KUVidGame.getShootedAtom().get(0).direction.getY(), directest.getY());
	}
	
	@Test 
	void testRemoved() { 
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
		KUVidGame.getInstance().getShooter().pickAtom();
		KUVidGame.getInstance().getShooter().shootAmmo();
		assertEquals(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.ALPHA)).size(), 0);
	}
	
	@Test
	void testNewAtom() {
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
		DomainFactory.getInstance().createAtom(AtomType.BETA, 1);
		KUVidGame.getInstance().getShooter().pickAtom();
		KUVidGame.getInstance().getShooter().shootAmmo();
		assertFalse(KUVidGame.getInstance().getShooter().currentAmmo.equals(null));
	}
	
}
