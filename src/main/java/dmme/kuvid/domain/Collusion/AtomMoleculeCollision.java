package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.GameObjects.*;

public class AtomMoleculeCollision implements collisionHandler{

    public AtomMoleculeCollision(GameObject object1, GameObject object2) {
        collisionHandler.collusion(object1, object2);
        
        if(object1.getSubType().toString().equals(object2.getSubType().toString())) {
        	 Player.getInstance().incrementPoint(1);
        }
    }

}
