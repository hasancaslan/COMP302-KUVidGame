package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.ui.AtomUI;

public abstract class Atom extends GameObject{
	
	public ObjectType type = ObjectType.ATOM;

	public Atom(Position position, Position direction, boolean active) {
		super(position, direction, active);
		// TODO Auto-generated constructor stub
	}

	public static void addPropertyListener(String string, AtomUI atomUI) {
		// TODO Auto-generated method stub
		
	}

}
