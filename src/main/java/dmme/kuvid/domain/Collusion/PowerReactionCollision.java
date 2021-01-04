package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;

public class PowerReactionCollision implements collisionHandler {
	
	public PowerReactionCollision(GameObject object1, GameObject object2) {
		String o1=object1.getSubType().toString();
		String power=o1.substring(0,o1.length()-2);
		String o2=object2.getSubType().toString();
		String blocker=o2.substring(0,o2.length()-2);
		
		if(power.equals(blocker)) {
			collisionHandler.collusion(object1, object2);
		}else {
			KUVidGame.getShootedPower().remove(object1);
			object1.setActive(false);
		}
		
	}
}
