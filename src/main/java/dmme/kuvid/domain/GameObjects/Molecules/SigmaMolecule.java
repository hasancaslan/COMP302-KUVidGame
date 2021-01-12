package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;

public class SigmaMolecule extends Molecule{

	private transient MovementStrategy strategy;
	
	public SigmaMolecule(Position position,Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
		this.setPattern(new StraightPatternStrategy());
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMoleculeType() {
		// TODO Auto-generated method stub
		this.subtype=MoleculeType.SIGMA;
	}
	
	public void move() {
		this.strategy=new StraightPatternStrategy();
		this.strategy.move(this, count);
		this.count++;
		if(spinning) {
			this.spin = this.count/2;
			this.spin = this.spin % 4;	
		}
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		publishPropertyEvent("position",this.position,position);
		this.position = position;
	}

	@Override
	public void setPattern(MovementStrategy strategy) {
		// TODO Auto-generated method stub
		this.strategy=strategy;
		
	}

	@Override
	public MovementStrategy getPattern() {
		// TODO Auto-generated method stub
		return this.strategy;
	}

}
