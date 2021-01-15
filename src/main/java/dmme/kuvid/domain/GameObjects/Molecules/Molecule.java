package dmme.kuvid.domain.GameObjects.Molecules;

import com.google.gson.annotations.SerializedName;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;

public abstract class Molecule extends GameObject{
	
	protected boolean spinning = KUVidGame.getInstance().getSpinning();
	public MoleculeType subtype;
	protected int count;
	protected int spin;

	public Molecule(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		this.setMoleculeType();
		this.count = 0;
		this.spin = 0;
	}
	

	public Enum getSubType() {
		return this.subtype;
	}
	
	public abstract void setPattern(MovementStrategy strategy);
	
	public void move() {
		this.getPattern().move(this, count);
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
	
	public abstract MovementStrategy getPattern() ;


	public abstract void setMoleculeType();

	@Override
	public String toString() {
		return "Molecule [" +
				"spinning=" + spinning +
				", subtype=" + subtype +
				", count=" + count +
				", spin=" + spin +
				", strategy=" + this.getPattern() +
				", L=" + L +
				", gameField=" + gameField +
				", position=" + position +
				", direction=" + direction +
				", propertyListenersMap=" + propertyListenersMap +
				']';
	}
}
