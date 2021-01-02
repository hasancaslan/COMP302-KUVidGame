package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.ObjectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GammaAtom extends Atom{

	private List<Integer> gammaList = Arrays.asList(29,32,33);

	public GammaAtom(Position position, Position direction, boolean active, ObjectType type) {
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
		this.subtype=AtomType.GAMMA;
		
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
		this.neutrons = gammaList.get(new Random().nextInt(gammaList.size()));
		this.protons = 32;
		this.stability_constant = 0.8;
		this.efficiency = stability_constant + Math.abs((neutrons-protons))/2/protons;
		//Gamma stability constant +  ( | # of neutrons - # of protons | /  (2 * # of protons) )
	}

}
