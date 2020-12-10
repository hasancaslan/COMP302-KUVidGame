package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.Position;

public class ZigZagPatternStrategy implements MovementStrategy {
	
	static int count=0;
	public ZigZagPatternStrategy() {
		
	}
	
	@Override
	public void move(Molecule m){
		int Y = m.getPosition().getY();
		int X = m.getPosition().getX();
		double distance = 10*L * Math.sqrt(0.5);
		if(count%2==0) {
			X -= distance;
			Y += distance;
		} else {
			X += distance;
			Y += distance;
		}
		Position nextPosition = new Position(X, Y);	
		m.setPosition(nextPosition); 	
		count++;
	}
		
}
