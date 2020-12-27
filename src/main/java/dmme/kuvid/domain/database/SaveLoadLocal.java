package dmme.kuvid.domain.database;

import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.Key;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveLoadLocal implements Saveable, Loadable {
    private final HashMap<Key, List<GameObject>> gameObjectMap = KUVidGame.getGameObjectMap();
    private Player player;

    public SaveLoadLocal() {
    }

    @Override
    public boolean save() {
        JSONArray jsonArray = new JSONArray();
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
