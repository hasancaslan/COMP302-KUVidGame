package dmme.kuvid.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class betaMoleculeUI extends MoleculeUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	
	private GameObject mol;
	private GamePanel panel;
	
	public betaMoleculeUI(GameObject mol, GamePanel panel2) {
		super(IconImporter.getIconFromFileName("beta-2.png","molecules",new Dimension((int) (10 * L), (int) (10 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (10 * L));
        this.setSize(dimension);
        
        mol.addPropertyListener("active",this);
        mol.addPropertyListener("position",this);
        this.mol=mol;
        this.panel=panel2;
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
        	if((boolean) e.getNewValue()) {
        		this.panel.add(this);
        	}else {
        		this.panel.remove(this);
        	}
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
        }
    }
	
}
