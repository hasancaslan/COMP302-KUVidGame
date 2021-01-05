package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;

public class AtomMoleculeCollision implements collisionHandler{
	private boolean blocked=false;

    public AtomMoleculeCollision(GameObject object1, GameObject object2,boolean blocked) {
        collisionHandler.collusion(object1, object2);
        this.blocked=blocked;
        if(object1.getSubType().toString().equals(object2.getSubType().toString()) && !this.blocked) {
        	 //Player.getInstance().incrementPoint(1);
            Player.getInstance().incrementPoint( ((Atom) object1).getStability());
        }
    }

}
