package dmme.kuvid.domain.GameObjects.Powerup;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;

public abstract class PowerUp extends GameObject{
	
	public ObjectType type= ObjectType.POWER_UP;

	public PowerUp(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction,  active, type);
	}

}
