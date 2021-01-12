package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;

public class StraightPatternStrategy implements MovementStrategy {
	
	public StraightPatternStrategy() {
		
	}
	
	@Override
	public void move(GameObject m, int count){
		int Y = m.getPosition().getY();
		int X = m.getPosition().getX();
		Y += L; 
		
		Position nextPosition = new Position(X, Y);	
		m.setPosition(nextPosition); 	
	}	
	
}
