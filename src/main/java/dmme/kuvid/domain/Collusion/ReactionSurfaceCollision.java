package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.GameObject;

public class ReactionSurfaceCollision implements collisionHandler {
	int damage;

		public ReactionSurfaceCollision(GameObject object) {
			int shooterPosition= KUVidGame.getInstance().getShooter().getPosition();
			int objectPosition= object.getPosition().getX();
			
			if (Math.abs(shooterPosition - objectPosition) <10*KUVidGame.getInstance().getL()) {
				damage = 1000;
			}else {
				damage = KUVidGame.getInstance().getPlayableArea().width / Math.abs(shooterPosition - objectPosition);
			}
			Player.getInstance().decrementHealth(damage);
			destroyHandler.destroyObject(object);
		}
}
