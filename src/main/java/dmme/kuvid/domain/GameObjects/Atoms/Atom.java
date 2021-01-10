package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.ui.AtomUI;

public abstract class Atom extends GameObject{
	
	public AtomType subtype;

	protected double stability_constant;
	protected int protons;
	protected int neutrons;
	protected double efficiency;


	public Atom(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
	}

	//TODO
	public Atom() {
		super();
	}


	public abstract void setAtomType();

	
	public Enum getSubType() {
		return this.subtype;
	}

	public abstract void initStabilityFeatures(); //TODO may be deprecated, or renamed to setStability()
	public abstract double getStability();

}
