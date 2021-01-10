package dmme.kuvid.domain.GameObjects.Powerup;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.PowerType;

public abstract class PowerUp extends GameObject{
	
	public ObjectType type= ObjectType.POWER_UP;
	
	public PowerType subtype;

	public PowerUp(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction,  active, type);
		this.setPowerType();
	}
	
	public abstract void setPowerType();
	
	public Enum getSubType() {
		return this.subtype;
	}

}
