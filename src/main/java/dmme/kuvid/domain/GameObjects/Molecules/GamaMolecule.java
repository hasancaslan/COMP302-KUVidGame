package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;

public class GamaMolecule extends Molecule{

	public GamaMolecule(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setMoleculeType() {
		// TODO Auto-generated method stub
		this.subtype=MoleculeType.GAMMA;
	}

}
