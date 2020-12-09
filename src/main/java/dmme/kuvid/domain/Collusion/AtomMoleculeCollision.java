package dmme.kuvid.domain.Collusion;

import dmme.kuvid.domain.GameObjects.*;

public class AtomMoleculeCollision implements collisionHandler{

    public AtomMoleculeCollision(GameObject object1, GameObject object2) {
        collisionHandler.collusion(object1, object2);
    }

}
