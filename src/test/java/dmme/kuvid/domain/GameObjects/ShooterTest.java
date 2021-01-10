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

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        new GameFrame();
    }

    @BeforeEach
    void setUp() {
        new Shooter();
    }

    @AfterEach
    void tearDown() {
        //TODO
        KUVidGame.getInstance().getShooter().pickAtom();
        while (KUVidGame.getInstance().getShooter().currentAtom!=null) {
            KUVidGame.getInstance().getShooter().pickAtom();
            KUVidGame.getInstance().getShooter().shootAtom();
        }

    }

    @Test
    void testRandomPicking() {
        DomainFactory.createAtom(AtomType.ALPHA, 1);
        DomainFactory.createAtom(AtomType.SIGMA, 1);

        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);

        boolean alphaPicked = false;
        boolean sigmaPicked = false;

        Enum currAmmoType ;

        while (!(alphaPicked && sigmaPicked)) {
            KUVidGame.getInstance().getShooter().pickAtom();
            currAmmoType = KUVidGame.getInstance().getShooter().currentAtom.getSubType();
            assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());

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
        DomainFactory.createAtom(AtomType.ALPHA, 1);
        DomainFactory.createAtom(AtomType.SIGMA, 1);

        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);



        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.ALPHA
                    || KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.SIGMA);


        Enum currAmmoType = KUVidGame.getInstance().getShooter().currentAtom.getSubType();
        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom((AtomType) currAmmoType ),0);


        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.ALPHA);

        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),0);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),0);
    }


    @Test
    void testAtomOut() {
        DomainFactory.createAtom(AtomType.ALPHA, 1);
        KUVidGame.getInstance().getShooter().pickAtom();
        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),0);


        KUVidGame.getInstance().getShooter().pickAtom();
        assertThrows(NullPointerException.class, () -> KUVidGame.getInstance().getShooter().currentAtom.isActive());
    }

        @Test
    void pickAtomALPHA() {
            DomainFactory.createAtom(AtomType.ALPHA, 2);
            assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),2);

            KUVidGame.getInstance().getShooter().pickAtom();
            assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
            assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.ALPHA);

            KUVidGame.getInstance().getShooter().shootAtom();
            assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),1);

            KUVidGame.getInstance().getShooter().pickAtom();
            assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
            assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.ALPHA);
    }

    @Test
    void pickAtomBETA() {
        DomainFactory.createAtom(AtomType.BETA, 2);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.BETA),2);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.BETA);

        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.BETA),1);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.BETA);
    }

    @Test
    void pickAtomGAMMA() {
        DomainFactory.createAtom(AtomType.GAMMA, 2);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.GAMMA),2);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.GAMMA);

        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.GAMMA),1);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.GAMMA);
    }

    @Test
    void pickAtomSIGMA() {
        DomainFactory.createAtom(AtomType.SIGMA, 2);
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),2);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.SIGMA);

        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.SIGMA),1);

        KUVidGame.getInstance().getShooter().pickAtom();
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());
        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.getSubType()==AtomType.SIGMA);
    }
}