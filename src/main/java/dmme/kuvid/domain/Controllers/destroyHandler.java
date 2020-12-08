package dmme.kuvid.domain.Controllers; 
 
import dmme.kuvid.domain.GameObjects.GameObject; 
 
import java.util.List; 
 
public class destroyHandler { 
 
    public static boolean destroyObject(GameObject object1) {
        if (object1 == null) return false;
        GameObject.getGameObjectList().remove(object1);
        return true; 
    }
}
