package dmme.kuvid.domain.database;

import com.google.gson.GsonBuilder;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;


class SaveLoadFileTest {
    List<GameObject> list;
    AlphaAtom alphaAtom;
    SaveLoadFile saveLoadFile;

    @BeforeEach
    void setUp() {
        saveLoadFile = new SaveLoadFile();
        alphaAtom = new AlphaAtom(new Position(0,0), new Position(0,0), false, ObjectType.ATOM);
        list = new ArrayList<>();
        list.add(alphaAtom);

        KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.ALPHA), list);
    }

    @Nested
    @DisplayName("Tests for the method save")
    class Save {
        @Test
        void testProduceCorrectJSON() {
            String jsonStringFromMethod = saveLoadFile.gameObjectToJson(ObjectType.ATOM, AtomType.ALPHA);
            String jsonStringManual = new GsonBuilder().setPrettyPrinting().create().toJson(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.ALPHA)));

            Assertions.assertEquals(jsonStringManual, jsonStringFromMethod);
        }

        @Test
        void testProduceCorruptedJSON() {
            String jsonStringFromMethod = saveLoadFile.gameObjectToJson(ObjectType.ATOM, AtomType.ALPHA);
            String jsonStringManual = new GsonBuilder().setPrettyPrinting().create().toJson(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.BETA)));

            Assertions.assertNotEquals(jsonStringManual, jsonStringFromMethod);
        }
    }


    @Test
    @Disabled("Not Implemented yet")
    void loadGame() {

    }
}