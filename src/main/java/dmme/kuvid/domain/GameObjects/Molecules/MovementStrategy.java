package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.Position;

public interface MovementStrategy {
	
	int L = KUVidGame.getInstance().getL(); 
	int N = KUVidGame.getInstance().getN(); 
	
	public void move(Molecule m);
}
