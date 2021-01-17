package dmme.kuvid.domain.database;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.utils.PathHandler;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


class SaveLoadFileTest {
    List<GameObject> list;
    AlphaAtom alphaAtom;
    SaveLoadFile saveLoadFile;
    PathHandler pathHandler = PathHandler.getInstance();

    @BeforeEach
    void setUp() {
        saveLoadFile = new SaveLoadFile();
        alphaAtom = new AlphaAtom(new Position(0,0), new Position(0,0), false, ObjectType.ATOM);
        list = new ArrayList<>();
        list.add(alphaAtom);

        KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.ALPHA), list);
    }

    @Nested
    @DisplayName("Tests for saving")
    class Save {
        @Test
        void testProduceCorrectJSON() {
            String jsonStringFromMethod = saveLoadFile.gameObjectToJson(ObjectType.ATOM, AtomType.ALPHA);
            List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.ALPHA));
            String jsonStringManual = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create()
                    .toJson(list,new TypeToken<List<Atom>>(){}.getType());

            Assertions.assertEquals(jsonStringManual, jsonStringFromMethod);
        }

        @Test
        void testProduceCorruptedJSON() {
            String jsonStringFromMethod = saveLoadFile.gameObjectToJson(ObjectType.ATOM, AtomType.ALPHA);
            List<GameObject> list = KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.BETA));

            String jsonStringManual = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create()
                    .toJson(list,new TypeToken<List<Atom>>(){}.getType());

            Assertions.assertNotEquals(jsonStringManual, jsonStringFromMethod);
        }

        @Test
        void testFileSavedCorrectly() {
            String fileName = "test_data";
            saveLoadFile.save(ObjectType.ATOM, AtomType.BETA, fileName);

            String path = pathHandler.makePath("./snapshots", fileName + ".json");
            File tmpDir = new File(path);
            Assertions.assertTrue(tmpDir.delete());
        }
    }

    @Nested
    @DisplayName("Tests for loading")
    class Load {

        @Test
        void testFileLoaded() {
            String json = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create()
                    .toJson(list,new TypeToken<List<Atom>>(){}.getType());

            saveLoadFile.save(ObjectType.ATOM, AtomType.ALPHA, "test_alpha");
            String loaded = saveLoadFile.load("test_alpha");

            Assertions.assertEquals(loaded, json);
            String path = pathHandler.makePath("./snapshots", "test_alpha" + ".json");
            File tmpDir = new File(path);
            Assertions.assertTrue(tmpDir.delete());
        }

        @Test
        void testObjectDeserializedCorrectly() {
            saveLoadFile.save(ObjectType.ATOM, AtomType.ALPHA, "test_alpha");
            String loaded = saveLoadFile.load("test_alpha");
            List<GameObject> alphaAtomList = saveLoadFile.jsonToGameObject(loaded, ObjectType.ATOM, AtomType.ALPHA);


            Assertions.assertSame(list.get(0).getDirection().getX(), alphaAtomList.get(0).getDirection().getX());
            Assertions.assertSame(list.get(0).getPosition().getX(), alphaAtomList.get(0).getPosition().getX());
            Assertions.assertSame(list.get(0).getDirection().getY(), alphaAtomList.get(0).getDirection().getY());
            Assertions.assertSame(list.get(0).getPosition().getY(), alphaAtomList.get(0).getPosition().getY());
            Assertions.assertSame(list.get(0).getType(), alphaAtomList.get(0).getType());
            Assertions.assertSame(list.get(0).getSubType(), alphaAtomList.get(0).getSubType());

            String path = pathHandler.makePath("./snapshots", "test_alpha" + ".json");
            File tmpDir = new File(path);
            Assertions.assertTrue(tmpDir.delete());
        }
    }

}
