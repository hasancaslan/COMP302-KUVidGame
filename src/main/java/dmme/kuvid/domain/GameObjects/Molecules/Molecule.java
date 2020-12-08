package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.GameObjects.*;

public abstract class Molecule extends GameObject{

	public Molecule(Position position, Position direction, boolean active) {
		super(position, direction, active);
	}

}
