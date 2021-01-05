package dmme.kuvid.domain.GameObjects.Powerup;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.PowerType;

public abstract class PowerUp extends GameObject{

	
	public PowerType subtype;

	public PowerUp(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction,  active, type);
		this.setPowerType();
	}
	
	public abstract void setPowerType();
	
	public Enum getSubType() {
		return this.subtype;
	}

	@Override
	public String toString() {
		return "PowerUp [" +
				"subtype=" + subtype +
				", L=" + L +
				", gameField=" + gameField +
				", position=" + position +
				", direction=" + direction +
				", propertyListenersMap=" + propertyListenersMap +
				']';
	}
}
