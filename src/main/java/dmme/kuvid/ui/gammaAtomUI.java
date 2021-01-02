package dmme.kuvid.ui;

import java.awt.Dimension;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class gammaAtomUI extends AtomUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	private GameObject atom;
	
	private GamePanel panel;
	
	public gammaAtomUI(GameObject atom, GamePanel panel) {
		super(IconImporter.getIconFromFileName("gamma.png","atoms",new Dimension((int) (10 * L), (int) (10 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (10 * L));
        this.setSize(dimension);
        
        atom.addPropertyListener("active",this);
        atom.addPropertyListener("position",this);
        this.atom=atom;
        this.panel=panel;
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.atom.getPosition().getX(),this.atom.getPosition().getY()-10*L);
        	if((boolean) e.getNewValue()) {
        		this.panel.add(this);
        	}else {
        		this.panel.remove(this);
        	}
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.atom.getPosition().getX(),this.atom.getPosition().getY()-10*L);
        }
    }


	

}
