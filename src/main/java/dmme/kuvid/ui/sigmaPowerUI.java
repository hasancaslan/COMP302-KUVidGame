package dmme.kuvid.ui;

import java.awt.Dimension;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class sigmaPowerUI extends AtomUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	private GameObject power;
	
	private GamePanel panel;
	private int gameHeight;
	
	public sigmaPowerUI(GameObject power, GamePanel panel) {
		super(IconImporter.getIconFromFileName("+sigma-b.png","powerups",new Dimension((int) (10 * L), (int) (10 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (10 * L));
        this.setSize(dimension);
        power.addPropertyListener("active",this);
        power.addPropertyListener("position",this);
        this.power=power;
        this.panel=panel;
        this.gameHeight=KUVidGame.getInstance().getPlayableArea().height;

	
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.power.getPosition().getX(),this.power.getPosition().getY()-10*L);
        	if((boolean) e.getNewValue()) {
        		this.panel.add(this);
        	}else {
        		this.panel.remove(this);
        	}
        }else if (e.getPropertyName().equals("position")) {
        	if(this.power.isActive()) {
        		this.setLocation(this.power.getPosition().getX(),this.power.getPosition().getY());
        	}        
        }
    }


}

