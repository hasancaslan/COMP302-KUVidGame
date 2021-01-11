package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;

public interface collisionHandler {
	
	public static void collusion(GameObject object1, GameObject object2) {
		
		if(object1.getType().equals(ObjectType.ATOM)) {
			if(KUVidGame.getShootedAtom().contains(object1)) {
				destroyHandler.getInstance().destroyObject(object1);
			}
		}else if(object1.getType().equals(ObjectType.POWER_UP)) {
			if(KUVidGame.getShootedPower().contains(object1)) {
				KUVidGame.getShootedPower().remove(object1);
				object1.setActive(false);
			}
		}
		destroyHandler.getInstance().destroyObject(object2);
	};
	

}
