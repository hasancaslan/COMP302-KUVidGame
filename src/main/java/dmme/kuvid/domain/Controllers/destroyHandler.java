package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.GameObjects.GameObject;

public class destroyHandler {

    public static boolean destroyObject(GameObject object) {
        if (!GameObject.getGameObjectList().contains(object)) return false;
        GameObject.getGameObjectList().remove(object);
        return true;
    }


}
