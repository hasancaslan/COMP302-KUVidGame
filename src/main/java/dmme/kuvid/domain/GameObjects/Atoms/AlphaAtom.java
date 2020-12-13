package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.ObjectType;


public class AlphaAtom extends Atom{

	public AlphaAtom(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
		this.setAtomType();
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAtomType() {
		// TODO Auto-generated method stub
		this.subtype=AtomType.ALPHA;
		
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		publishPropertyEvent("position",this.position,position);
		this.position = position;
	}

}
