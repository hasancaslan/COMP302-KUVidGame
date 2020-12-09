package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;

public abstract class Molecule extends GameObject{
	
	public MoleculeType subtype;

	public Molecule(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
	}

	public Enum getSubType() {
		return this.subtype;
	}
	
	public abstract void setMoleculeType();
}
