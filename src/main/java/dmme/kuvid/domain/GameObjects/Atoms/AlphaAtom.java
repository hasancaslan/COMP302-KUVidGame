package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.ObjectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AlphaAtom extends Atom{

	private List<Integer> alphaList = Arrays.asList(7,8,9);

	public AlphaAtom(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
		this.setAtomType();
		this.initStabilityFeatures();
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




	@Override
	public double getStability() {
		return this.efficiency;
	}

	@Override
	public void initStabilityFeatures() {
		this.neutrons = alphaList.get(new Random().nextInt(alphaList.size()));
		this.protons = 8;
		this.stability_constant = 0.85;
		this.efficiency = (1- Math.abs(neutrons - protons)/protons)*stability_constant;
		//(1 - (| # of neutrons - # of protons | /  # of protons) ) * Alpha stability constant
	}

}
