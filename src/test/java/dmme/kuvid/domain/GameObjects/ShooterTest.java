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
        //TODO not needed for now
        KUVidGame.getInstance().getShooter().pickAtom();
        while (KUVidGame.getInstance().getShooter().currentAtom!=null) {
            KUVidGame.getInstance().getShooter().pickAtom();
            KUVidGame.getInstance().getShooter().shootAtom();
        }

    }

    @Test
    void testAtomRemains() {
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
    void testAtomOut() {
        DomainFactory.createAtom(AtomType.ALPHA, 1);
        KUVidGame.getInstance().getShooter().pickAtom();
        KUVidGame.getInstance().getShooter().shootAtom();
        assertEquals(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA),0);


        KUVidGame.getInstance().getShooter().pickAtom();
        assertThrows(NullPointerException.class, () -> KUVidGame.getInstance().getShooter().currentAtom.isActive());
    }

        @Test
    void pickAtomALPHA() { //TODO

    }

    @Test
    void pickAtomBETA() {

    }

    @Test
    void pickAtomGAMMA() {

    }

    @Test
    void pickAtomSIGMA() {

    }
}