package dmme.kuvid.domain.Controllers;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Collusion.AtomMoleculeCollision;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.lib.types.ObjectType;

import java.sql.Time;
import java.util.List;

public class movementHandler {
	
	//BOYLE MI OLACAK ???
    private static movementHandler instance = null;
    
    public movementHandler() {
    }

    public static movementHandler getInstance() {
        if (instance == null)
            instance = new movementHandler();
        
        return instance;
    }
	//BOYLE MI OLACAK ???
    
    final int range = KUVidGame.getInstance().getRange();
    final int L = KUVidGame.getInstance().getL();
    final int N = KUVidGame.getInstance().getN();

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
    
    public void move() {
    	
        KUVidGame.getInstance().getL();
        List<GameObject> gameObjectList = GameObject.getGameObjectList();
        
	    for (GameObject gameObject : gameObjectList) {
	        if(gameObject.isActive()) {
		        int x1 = gameObject.getPosition().getX();
		        int y1 = gameObject.getPosition().getY();
		        int dx = gameObject.getDirection().getX();      		
		        int dy = gameObject.getDirection().getY();  
		        
		        int newX = x1 + dx;
		        int newY = y1 + dy;
		        
		        if(newX > N * L || newX < 0) {//bouncing from the wall
		        	newX = x1 - dx;				
		        	gameObject.getDirection().setX(-dx);      
		        }
	        	Position nextPosition = new Position(newX, newY);
	            gameObject.setPosition(nextPosition);
	        }
	    }
	    this.search();
    }

}