package dmme.kuvid.domain.GameObjects.ReactionBlocker;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.GameObjects.Molecules.StraightPatternStrategy;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.ReactionType;

public class SigmaReactionBlocker extends ReactionBlocker {

	public SigmaReactionBlocker(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockerType() {
		// TODO Auto-generated method stub
		this.subtype=ReactionType.SIGMA_R;
	}

	public void move() {
		this.setPattern(new StraightPatternStrategy());
		this.strategy.move(this, count);	
		this.count++;
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		publishPropertyEvent("position",this.position,position);
		this.position = position;
	}
}
