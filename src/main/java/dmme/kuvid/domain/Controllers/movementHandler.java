package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Collusion.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

public class movementHandler {

    private static movementHandler instance = null;

    private movementHandler() {}

    public static movementHandler getInstance() {
        if (instance == null)
            instance = new movementHandler();

        return instance;
    }

    final int range = KUVidGame.getInstance().getRange();

    // TODO: CONFIGURE FOR SCREENSIZE NOT N
    //final int L = KUVidGame.getInstance().getL();
    //final int N = KUVidGame.getInstance().getN();

    public void search() {
        HashMap<Key,List<GameObject>> map= KUVidGame.getGameObjectMap();
        
       for (Key k: map.keySet()) {
    	   List<GameObject> gameObjectList = map.get(k);
        for (GameObject gameObject : gameObjectList) {
            if (!gameObject.isActive()) continue;
            int x1 = gameObject.getPosition().getX();
            int y1 = gameObject.getPosition().getY();
            ObjectType objectType = gameObject.getType();
            for (GameObject gameObject1 : gameObjectList) {
                if (!gameObject1.isActive()) continue;
                if (gameObject.equals(gameObject1)) continue;
                int x2 = gameObject1.getPosition().getX();
                int y2 = gameObject1.getPosition().getY();
                ObjectType objectType1 = gameObject1.getType();
                if ((x1 - x2 < range || x2 - x1 < range) && (y1 - y2 < range || y2 - y1 < range)) {
                    if ((objectType == ObjectType.ATOM && objectType1 == ObjectType.MOLECULE) || (objectType1 == ObjectType.ATOM && objectType == ObjectType.MOLECULE)) {
                        new AtomMoleculeCollision(gameObject, gameObject1);
                    } else if ((objectType == ObjectType.ATOM && objectType1 == ObjectType.REACTION_BLOCKER) || (objectType1 == ObjectType.ATOM && objectType == ObjectType.REACTION_BLOCKER)) {
                        new AtomReactionCollision(gameObject1, gameObject);
                    } else if ((objectType == ObjectType.MOLECULE && objectType1 == ObjectType.REACTION_BLOCKER) || (objectType1 == ObjectType.MOLECULE && objectType == ObjectType.REACTION_BLOCKER)) {
                        new MoleculeReactionCollision(gameObject, gameObject1);
                    } else if ((objectType == ObjectType.POWER_UP && objectType1 == ObjectType.REACTION_BLOCKER) || (objectType1 == ObjectType.POWER_UP && objectType == ObjectType.REACTION_BLOCKER)) {
                        new PowerReactionCollision(gameObject, gameObject1);
                    } else if ((objectType == ObjectType.POWER_UP && objectType1 == ObjectType.REACTION_BLOCKER) || (objectType1 == ObjectType.POWER_UP && objectType == ObjectType.REACTION_BLOCKER)) {
                        new PowerReactionCollision(gameObject, gameObject1);
                    } else if ((objectType == ObjectType.SHOOTER && objectType1 == ObjectType.POWER_UP)) {
                        new PowerShooterCollision(gameObject1);
                    } else if ((objectType == ObjectType.POWER_UP && objectType1 == ObjectType.SHOOTER)) {
                        new PowerShooterCollision(gameObject);
                    }
                }
            }
            if(objectType == ObjectType.REACTION_BLOCKER && y1-range == 0) {
                new ReactionSurfaceCollision(gameObject);
            }
        }
       }
    }

    public void move() {
    	HashMap<Key,List<GameObject>> map= KUVidGame.getGameObjectMap();
        for (Key k: map.keySet()) {
        	 List<GameObject> gameObjectList = map.get(k);
	        for (GameObject gameObject : gameObjectList) {
	            gameObject.move();
	        }
        }
        this.search();
    }

    public void run() {
            search();
            move();
    }
}