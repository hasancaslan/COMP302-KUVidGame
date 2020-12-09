package dmme.kuvid.domain.GameObjects.Powerup;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public class AlphaPowerUp extends PowerUp {

	public AlphaPowerUp(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum getSubType() {
		// TODO Auto-generated method stub
		return null;
	}

}
