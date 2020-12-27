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
			
			System.out.println("dist: "+Math.abs(shooterPosition - objectPosition));
			if (Math.abs(shooterPosition - objectPosition) <10*KUVidGame.getInstance().getL()) {
				damage = 100;
				System.out.println("KAFAMA DÜŞTÜ");
				//DOES NOT ENTER HERE CHECK!!!!!
			}else {
				damage = KUVidGame.getInstance().getPlayableArea().width / Math.abs(shooterPosition - objectPosition);
				System.out.println("YAKINA DÜŞTÜ: "+damage);
			}
			Player.getInstance().decrementHealth(damage);
			destroyHandler.destroyObject(object);
		}
}
