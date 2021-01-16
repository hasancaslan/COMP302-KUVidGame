package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.BlenderAction;
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

class ShooterTest {

    @BeforeEach
    void setUp() {
        KUVidGame.getInstance();
        KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.ALPHA)).clear();
	     KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.BETA)).clear();
	     KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.GAMMA)).clear();
	     KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.SIGMA)).clear();
    }

    @AfterEach
    void tearDown() {
        //TODO
        KUVidGame.getInstance().getShooter().pickAtom();
        while (KUVidGame.getInstance().getShooter().currentAmmo!=null) {
            KUVidGame.getInstance().getShooter().pickAtom();
            KUVidGame.getInstance().getShooter().shootAmmo();
        }

    }

    @Test
    void testRandomPicking() {
        DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
        DomainFactory.getInstance().createAtom(AtomType.SIGMA, 1);

        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);

        boolean alphaPicked = false;
        boolean sigmaPicked = false;

        Enum currAmmoType ;

        while (!(alphaPicked && sigmaPicked)) {
            KUVidGame.getInstance().getShooter().pickAtom();
            currAmmoType = KUVidGame.getInstance().getShooter().currentAmmo.getSubType();
            assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());

            if (currAmmoType == AtomType.ALPHA) {
                alphaPicked = true;
            } else if (currAmmoType == AtomType.SIGMA) {
                sigmaPicked = true;
            }
        }

        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);



    }

    @Test
    void testConsecutiveDifferentPickingWithShooting() {
        DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
        DomainFactory.getInstance().createAtom(AtomType.SIGMA, 1);

        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);



        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.ALPHA
                    || KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.SIGMA);


        Enum currAmmoType = KUVidGame.getInstance().getShooter().currentAmmo.getSubType();
        KUVidGame.getInstance().getShooter().shootAmmo();
        assertEquals(KUVidGame.getInstance().getNumAtom((AtomType) currAmmoType ),0);


        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        //assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.ALPHA);

        KUVidGame.getInstance().getShooter().shootAmmo();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),0);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),0);
    }


    @Test
    void testAtomOut() {
        DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
        KUVidGame.getInstance().getShooter().pickAtom();
        KUVidGame.getInstance().getShooter().shootAmmo();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),0);


        KUVidGame.getInstance().getShooter().pickAtom();
        assertThrows(NullPointerException.class, () -> KUVidGame.getInstance().getShooter().currentAmmo.isActive());
    }


        @Test
    void pickAtomALPHA() {
            DomainFactory.getInstance().createAtom(AtomType.ALPHA, 2);
            assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),2);

            KUVidGame.getInstance().getShooter().pickAtom();
            assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
            assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.ALPHA);

            KUVidGame.getInstance().getShooter().shootAmmo();
            assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);

            KUVidGame.getInstance().getShooter().pickAtom();
            assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
            assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.ALPHA);
    }



    @Test
    void pickAtomBETA() {
        DomainFactory.getInstance().createAtom(AtomType.BETA, 2);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.BETA),2);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.BETA);

        KUVidGame.getInstance().getShooter().shootAmmo();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.BETA),1);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.BETA);
    }

    @Test
    void pickAtomGAMMA() {
        DomainFactory.getInstance().createAtom(AtomType.GAMMA, 2);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.GAMMA),2);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.GAMMA);

        KUVidGame.getInstance().getShooter().shootAmmo();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.GAMMA),1);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.GAMMA);
    }

    @Test
    void pickAtomSIGMA() {
        DomainFactory.getInstance().createAtom(AtomType.SIGMA, 2);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),2);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.SIGMA);

        KUVidGame.getInstance().getShooter().shootAmmo();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAmmo.getSubType()==AtomType.SIGMA);
    }
}