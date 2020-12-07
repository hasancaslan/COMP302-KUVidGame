package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.*;

public interface collisionHandler {
	
	public static void collusion(GameObject object1, GameObject object2) {
		destroyHandler.destroyObject(object1, object2);
	};
	
//	istanceof(GameObject)

}
