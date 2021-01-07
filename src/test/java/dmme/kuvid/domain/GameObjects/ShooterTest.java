package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.BlenderAction;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import org.junit.jupiter.api.AfterEach;
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
        new Shooter();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAtomRemains() {

        DomainFactory.createAtom(AtomType.ALPHA, 1);

        KUVidGame.getInstance().getShooter().pickAtom();

        assertTrue(KUVidGame.getInstance().getShooter().currentAtom.isActive());

    }


    @Test
    void testAtomOut() {

        DomainFactory.createAtom(AtomType.ALPHA, 1);

        KUVidGame.getInstance().getShooter().shootAtom();

        assertThrows(NullPointerException.class, () -> KUVidGame.getInstance().getShooter().pickAtom());


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