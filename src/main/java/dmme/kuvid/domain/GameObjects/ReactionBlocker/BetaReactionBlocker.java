package dmme.kuvid.domain.GameObjects.ReactionBlocker;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public class BetaReactionBlocker extends ReactionBlocker {

	public BetaReactionBlocker(Position position, Position direction, boolean active, ObjectType type) {
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
