package dmme.kuvid.domain.GameObjects.Molecules;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;

public abstract class Molecule extends GameObject{
	
	protected boolean spinning = KUVidGame.getInstance().getSpinning();
	public MoleculeType subtype;
	protected int count;
	protected int spin;
	protected MovementStrategy strategy = new StraightPatternStrategy();

	public Molecule(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		this.setMoleculeType();
		this.count = 0;
		this.spin = 0;
	}
	

	public Enum getSubType() {
		return this.subtype;
	}
	
	public void setPattern(MovementStrategy strategy){
		this.strategy = strategy;
	}
	
	public void move() {
		this.strategy.move(this, count);
		this.count++;
		if(spinning) {
			this.spin = this.count/2;
			this.spin = this.spin % 4;	
		}
	}
	
	public int getSpin() {
		return spin;
	}


	public void setSpin(int spin) {
		this.spin = spin;
	}


	public abstract void setMoleculeType();
}
