package dmme.kuvid.domain.GameObjects.Molecules;

import java.awt.Dimension;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;

public interface MovementStrategy {
	
	int L = KUVidGame.getInstance().getL(); 
	Dimension gameField=KUVidGame.getInstance().getPlayableArea();
	
	public void move(GameObject m, int count);


}
