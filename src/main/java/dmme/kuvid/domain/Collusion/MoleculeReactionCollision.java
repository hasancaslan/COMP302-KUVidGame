package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.GameObjects.GameObject;

public class MoleculeReactionCollision implements collisionHandler {
	
	public MoleculeReactionCollision(GameObject object1, GameObject object2) {
		collisionHandler.collusion(object1, object2);
	}
}
