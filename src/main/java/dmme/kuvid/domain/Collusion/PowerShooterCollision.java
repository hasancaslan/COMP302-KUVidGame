package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.GameObjects.GameObject;

public class PowerShooterCollision implements collisionHandler {
	
	public PowerShooterCollision(GameObject object) {
		KUVidGame.getInstance().setNumPowerUp(KUVidGame.getInstance().getNumPowerUp() + 1);
		destroyHandler.getInstance().destroyObject(object);
	}
}
