package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.Position;

public class StraightPatternStrategy implements MovementStrategy {
	
	@Override
	public void move(Molecule m){
		int Y = m.getPosition().getY();
		int X = m.getPosition().getX();
		Y += 10*L; 
		
		Position nextPosition = new Position(X, Y);	
		m.setPosition(nextPosition); 	
	}	
}
