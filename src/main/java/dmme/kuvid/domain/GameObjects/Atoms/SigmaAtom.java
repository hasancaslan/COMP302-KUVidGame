package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.ObjectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SigmaAtom extends Atom{

	private List<Integer> sigmaList = Arrays.asList(63,64,67);

	public SigmaAtom(Position position, Position direction, boolean active, ObjectType type) {
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
		this.subtype=AtomType.SIGMA;
		
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
		this.neutrons = sigmaList.get(new Random().nextInt(sigmaList.size()));
		this.protons = 64;
		this.stability_constant = 0.7;
		this.efficiency = (1 + stability_constant)/2 + Math.abs(neutrons-protons)/protons;
		// (1 + Sigma stability constant) / 2 +  ( | # of neutrons - # of protons | /  # of protons)
	}


}
