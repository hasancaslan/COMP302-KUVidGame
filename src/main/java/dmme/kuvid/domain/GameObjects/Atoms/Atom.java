package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.ui.AtomUI;

public abstract class Atom extends GameObject{
	
	public AtomType subtype;

	public Atom(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
	}

	public static void addPropertyListener(String string, AtomUI atomUI) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void setAtomType();
	
	public Enum getSubType() {
		return this.subtype;
	}

}
