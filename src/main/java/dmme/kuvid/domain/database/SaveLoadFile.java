package dmme.kuvid.domain.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.KUVidGame;


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
}
