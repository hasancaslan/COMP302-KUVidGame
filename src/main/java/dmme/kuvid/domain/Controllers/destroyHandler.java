package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.lib.types.AtomType;

public class destroyHandler {

    public static boolean destroyObject(GameObject object1) {
        if (object1 == null) return false;
        GameObject.getGameObjectList().remove(object1);
        return true;
    }

    public static boolean blenderDestroy(GameObject object, AtomType type) {
        if (object == null) return false;

        return true;
    }
}
