package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.Collusion.AtomMoleculeCollision;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.lib.types.ObjectType;

import java.util.List;

public class movementHandler {
    final int range = 10;

    public void search() {
        List<GameObject> gameObjectList = GameObject.getGameObjectList();
        for (GameObject gameObject : gameObjectList) {
            int x1 = gameObject.getPosition().getX();
            int y1 = gameObject.getPosition().getY();
            for (GameObject gameObject1 : gameObjectList) {
                if (gameObject.equals(gameObject1)) continue;
                int x2 = gameObject1.getPosition().getX();
                int y2 = gameObject1.getPosition().getY();
                if ((x1 - x2 < range || x2 - x1 < range) && (y1 - y2 < range || y2 - y1 < range)) {
                    ObjectType objectType = gameObject.getType();
                    ObjectType objectType1 = gameObject1.getType();
                    if ((objectType == ObjectType.ATOM && objectType1 == ObjectType.MOLECULE) || (objectType1 == ObjectType.ATOM && objectType == ObjectType.MOLECULE)) {
                        AtomMoleculeCollision collision = new AtomMoleculeCollision();

                    } else if ((objectType == ObjectType.ATOM && objectType1 == ObjectType.POWER_UP) || (objectType1 == ObjectType.ATOM && objectType == ObjectType.POWER_UP)) {

                    } else if ((objectType == ObjectType.ATOM && objectType1 == ObjectType.REACTION_BLOCKER) || (objectType1 == ObjectType.ATOM && objectType == ObjectType.REACTION_BLOCKER)) {

                    }

            }
        }
    }




    }

}
