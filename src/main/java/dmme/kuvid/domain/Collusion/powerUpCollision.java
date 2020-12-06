package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.GameObjects.*;

public class powerUpCollision implements collisionHandler {

	@Override
	public void collusion(GameObject object1, GameObject object2) {
		final int range = 10;
		int x1 = object1.getPosition().getX();
		int y1 = object1.getPosition().getY();
		int x2 = object1.getPosition().getX();
		int y2 = object1.getPosition().getY();
		if ((x1 - x2 < range || x2 - x1 < range) && (y1 - y2 < range || y2 - y1 < range)) {
			//notify that collusion occurred
		}
	}

}
