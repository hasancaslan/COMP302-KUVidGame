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

			if (shooterPosition == objectPosition)
				damage = 1000000;
			else
				// TODO: CONFIGURE FOR SCREENSIZE NOT N
				//damage = (KUVidGame.getInstance().getN() * KUVidGame.getInstance().getL()) / Math.abs(shooterPosition - objectPosition);

			Player.getInstance().decrementHealth(damage);
			destroyHandler.destroyObject(object);
		}
}
