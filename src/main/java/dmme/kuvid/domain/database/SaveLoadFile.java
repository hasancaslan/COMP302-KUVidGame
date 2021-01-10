package dmme.kuvid.domain.database;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.utils.PathHandler;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class SaveLoadFile implements SaveMode {

    @Override
    public boolean saveGame() {

        return false;
    }

    @Override
    public boolean loadGame() {
        return false;
    }


    public String save(ObjectType objectType, Enum<?> subType, String name) {
        /*
        @requires: this not null, objectType not null, subType not null, name not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String and saves corresponding String as a ".json" file
         */
        PathHandler pathHandler = PathHandler.getInstance();

        String saveFolder = "snapshots";
        String saveFolderPath = pathHandler.makePath(".",saveFolder,"");
        File f = new File(saveFolderPath);

        if (!f.exists()) {
            f.mkdir();
        }

        String jsonString = "";
        try {
            FileWriter fileWriter = new FileWriter(pathHandler.makePath(f.getAbsolutePath(), name)+".json");
            fileWriter.write(gameObjectToJson(objectType, subType));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("SERIALIZED");
        //System.out.println("Alpha Atom Info: " + jsonString);
        return jsonString;
    }

    public String gameObjectToJson(ObjectType objectType, Enum<?> subType) {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
        return new GsonBuilder().setPrettyPrinting().create()
                .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)));
    }

    public String load(String fileName) {
        /*
        @requires: this not null, fileName not null
        @effects: finds corresponding json file, creates an object from list
        and returns the object as a JSON string.
         */
        PathHandler pathHandler = PathHandler.getInstance();

        String saveFolder = "snapshots";
        String saveFolderPath = pathHandler.makePath(".",saveFolder,"");
        File f = new File(saveFolderPath);
        String outputData = "adsf";

        if (!f.exists()) {
            return null;
        }

        List<GameObject> list;
        try {
            outputData = new String(Files.readAllBytes(Paths.get(pathHandler.makePath(f.getAbsolutePath(), fileName) + ".json")));
            list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.ALPHA);

            System.out.println(outputData);
            System.out.println(list.get(0).getType());
            System.out.println(list.get(0).getSubType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputData;
    }

    public List<GameObject> jsonToGameObject(String jsonString, ObjectType objectType, Enum<?> subType) {
        /*
        @requires: this not null, objectType not null, subType not null, jsonString not null
        @effects: Generates a List object from properties of the given jsonString.
            Returns generated object as a GameObject List.
         */
        Type type = null;
        if (objectType == ObjectType.ATOM) {
            if (subType == AtomType.ALPHA) {
                type = new TypeToken<List<AlphaAtom>>(){}.getType();
            } else if (subType == AtomType.BETA) {
                type = new TypeToken<List<BetaAtom>>(){}.getType();
            } else if (subType == AtomType.GAMMA) {
                type = new TypeToken<List<GamaAtom>>(){}.getType();
            } else if (subType == AtomType.SIGMA) {
                type = new TypeToken<List<SigmaAtom>>(){}.getType();
            }
        }
        return new GsonBuilder().setPrettyPrinting().create().fromJson(jsonString, type);
    }


}
