package dmme.kuvid.domain.database;

import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.Key;

import java.util.HashMap;
import java.util.List;

public class SaveLoadCloud implements Saveable, Loadable {
    private final HashMap<Key, List<GameObject>> gameObjectMap = KUVidGame.getGameObjectMap();
    private Player player;

    public SaveLoadCloud() {
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
