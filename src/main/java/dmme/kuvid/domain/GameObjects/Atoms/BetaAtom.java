package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.ObjectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BetaAtom extends Atom{

	private List<Integer> betaList = Arrays.asList(15,16,17,18,21);

	public BetaAtom(Position position, Position direction, boolean active, ObjectType type) {
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
		this.subtype=AtomType.BETA;
		
	}



	public double getStability() {
		return this.efficiency;
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		publishPropertyEvent("position",this.position,position);
		this.position = position;
	}



	@Override
	public void initStabilityFeatures() {
		this.neutrons = betaList.get(new Random().nextInt(betaList.size()));
		this.protons = 16;
		this.stability_constant = 0.9;
		this.efficiency = stability_constant- (0.5* Math.abs(neutrons-protons)/protons);
		//Beta stability constant - ( 0.5 * | # of neutrons - # of protons | /  # of protons)
	}
}
