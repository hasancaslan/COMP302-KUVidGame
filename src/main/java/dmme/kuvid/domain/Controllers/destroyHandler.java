package dmme.kuvid.domain.Controllers; 
 
import dmme.kuvid.domain.GameObjects.GameObject; 
 
import java.util.List; 
 
public class destroyHandler { 
 
    public static boolean destroyObject(GameObject object1, GameObject object2) { 
        if (object1 == null || object2 == null) return false; 
        GameObject.getGameObjectList().remove(object1); 
        GameObject.getGameObjectList().remove(object2); 
        return true; 
    } 
 
} 

import dmme.kuvid.domain.GameObjects.GameObject;

public class destroyHandler {

    public static boolean destroyObject(GameObject object) {
        if (!GameObject.getGameObjectList().contains(object)) return false;
        GameObject.getGameObjectList().remove(object);
        return true;
    }


}
