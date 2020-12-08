package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;

public abstract class Molecule extends GameObject{

	public Molecule(Position position, boolean active, ObjectType type) {
		super(position, active, type);
	}

}
