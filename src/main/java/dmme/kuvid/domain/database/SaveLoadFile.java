package dmme.kuvid.domain.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.utils.PathHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SaveLoadFile implements SaveMode {

    @Override
    public boolean saveGame() {

        return false;
    }

    @Override
    public boolean loadGame() {
        return false;
    }

    private void serialize() {
        Gson gson = new Gson();
        String data = gson.toJson(KUVidGame.getGameObjectMap());
    }

    private void deserialize(Gson gson, String jsonString) {
        //ype type = new TypeToken<HashMap<String, List<?>>>(){}.getType();
        //HashMap<> clonedMap = gson.fromJson(jsonString, type);
    }

    public String save(ObjectType objectType, Enum<?> subType, String name) {
        /*
        REQUIRES: this not null, objectType not null, subType not null, name not null
        EFFECTS: Generates a JSON string from properties of the given specified List of GameObjects.
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
            fileWriter.write(gameObjectToJson(ObjectType.ATOM, AtomType.ALPHA));
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
        REQUIRES: this not null, objectType not null, subType not null
        EFFECTS: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
        return new GsonBuilder().setPrettyPrinting().create()
                .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)));
    }

}
