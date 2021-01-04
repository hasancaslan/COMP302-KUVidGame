package dmme.kuvid.domain.GameObjects.ReactionBlocker;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Molecules.MovementStrategy;
import dmme.kuvid.domain.GameObjects.Molecules.StraightPatternStrategy;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.ReactionType;

public abstract class ReactionBlocker extends GameObject{
	
	public ObjectType type = ObjectType.REACTION_BLOCKER;
	public ReactionType subtype;
	protected int count;
	protected MovementStrategy strategy = new StraightPatternStrategy();

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
	
	public abstract void setBlockerType();
}
