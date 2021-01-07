package dmme.kuvid.domain.Controllers;

import java.util.List;

import java.util.Random;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.utils.observer.Observable;

public class destroyHandler extends Observable{
	
	private static Random rand=new Random();
	private static destroyHandler instance=null;
	
	private destroyHandler() {
		
	}
	
	public static destroyHandler getInstance() {
		 if (instance == null)
	            instance = new destroyHandler();

	        return instance;
	}

    public boolean destroyObject(GameObject object1) {
        if (object1 == null) return false;
        if(object1.getType().equals(ObjectType.ATOM)) {
        	KUVidGame.getShootedAtom().remove(object1);
        }else{
        	GameObject.getGameObjectList(object1.getType(), object1.getSubType()).remove(object1);
        }
        object1.setActive(false);
        publishPropertyEvent("updateAtom",null,null);
        
        return true;
    }

    public boolean blenderDestroy(AtomType type) {
        
        List<GameObject> atomList=KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,type));
        
        GameObject atom=atomList.get(rand.nextInt(atomList.size()));
        
        while(atom.isActive()) {
            atom=atomList.get(rand.nextInt(atomList.size()));
        }
        
        atomList.remove(atom);

        publishPropertyEvent("updateAtom",null,null);
        return true;
    }
}
