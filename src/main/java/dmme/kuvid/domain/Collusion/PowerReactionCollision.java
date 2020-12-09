package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.GameObjects.GameObject;

public class PowerReactionCollision implements collisionHandler {
	
	public PowerReactionCollision(GameObject object1, GameObject object2) {
		collisionHandler.collusion(object1, object2);
	}
}
