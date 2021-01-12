package dmme.kuvid.domain.GameObjects.ReactionBlocker;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Molecules.MovementStrategy;
import dmme.kuvid.domain.GameObjects.Molecules.StraightPatternStrategy;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.ReactionType;

public abstract class ReactionBlocker extends GameObject{

	public ReactionType subtype;
	protected int count;
	protected transient MovementStrategy strategy = new StraightPatternStrategy();

	public ReactionBlocker(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		this.setBlockerType();
		this.count = 0;
	}
	
	public Enum getSubType() {
		return this.subtype;
	}
	
	public void setPattern(MovementStrategy strategy){
		this.strategy = strategy;
	}
	
	public void move() {
		this.strategy.move(this, count);
	}
	
	public MovementStrategy getPattern() {
		return this.strategy;
	}
	
	public abstract void setBlockerType();

	@Override
	public String toString() {
		return "ReactionBlocker [" +
				"subtype=" + subtype +
				", count=" + count +
				", strategy=" + strategy +
				", L=" + L +
				", gameField=" + gameField +
				", position=" + position +
				", direction=" + direction +
				", propertyListenersMap=" + propertyListenersMap +
				']';
	}
}
