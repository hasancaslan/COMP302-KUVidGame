package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;

public interface collisionHandler {
	
	public static void collusion(GameObject object1, GameObject object2) {
		
		if(object1.getType().equals(ObjectType.ATOM)) {
			if(KUVidGame.getShootedAtom().contains(object1)) {
				destroyHandler.destroyObject(object1);
			}
		}
		destroyHandler.destroyObject(object2);
	};
	
//	istanceof(GameObject)

}
