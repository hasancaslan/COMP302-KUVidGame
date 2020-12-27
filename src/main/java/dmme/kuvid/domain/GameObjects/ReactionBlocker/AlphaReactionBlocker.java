package dmme.kuvid.domain.GameObjects.ReactionBlocker;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.GameObjects.Molecules.ZigZagPatternStrategy;
import dmme.kuvid.lib.types.MoleculeType;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.lib.types.ReactionType;

public class AlphaReactionBlocker extends ReactionBlocker {

	public AlphaReactionBlocker(Position position, Position direction, boolean active, ObjectType type) {
		super(position, direction, active, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Collusion() {
		// TODO Auto-generated method stub
		
	}

	public void move() {
		this.setPattern(new ZigZagPatternStrategy());
		this.strategy.move(this, count);	
		this.count++;
	}

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		publishPropertyEvent("position",this.position,position);
		this.position = position;
	}

	@Override
	public void setBlockerType() {
		// TODO Auto-generated method stub
		this.subtype=ReactionType.ALPHA_R;
	}
	
}
