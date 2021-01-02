package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;

public class ZigZagPatternStrategy implements MovementStrategy {
	

	public ZigZagPatternStrategy() {

	}
	
	@Override
	public void move(GameObject m, int count){
		int Y = m.getPosition().getY();
		int X = m.getPosition().getX();
		double distance = L * Math.sqrt(0.5);
		if(Math.floorDiv(count,10) % 2 == 0) {
			X -= distance;
			Y += distance;
		}else {
			X += distance;
			Y += distance;
		}
		Position nextPosition = new Position(X, Y);
		m.setPosition(nextPosition);

	}

}
