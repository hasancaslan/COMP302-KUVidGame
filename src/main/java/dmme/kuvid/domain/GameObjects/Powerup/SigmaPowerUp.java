package dmme.kuvid.domain.GameObjects.Powerup;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.PowerType;

public class SigmaPowerUp extends PowerUp {

	public SigmaPowerUp(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		publishPropertyEvent("position",this.position,position);
		this.position = position;
	}

	@Override
	public void setPowerType() {
		// TODO Auto-generated method stub
		this.subtype=PowerType.SIGMA_B;
	}

}
