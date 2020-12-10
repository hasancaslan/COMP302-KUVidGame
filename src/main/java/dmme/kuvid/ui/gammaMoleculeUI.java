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

public class gammaMoleculeUI extends MoleculeUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	
	private GameObject mol;
	private GameFrame panel;
	
	public gammaMoleculeUI(GameObject mol, GameFrame panel) {
		super(IconImporter.getIconFromFileName("gamma-.png","molecules",new Dimension((int) (0.1 * L), (int) (0.1 * L))));
        Dimension dimension = new Dimension((int) (0.1 * L), (int) (0.1 * L));
        this.setSize(dimension);
        
        mol.addPropertyListener("active",this);
        this.mol=mol;
        this.panel=panel;
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
            //this.panel.getContentPane().add(this);
            setOpaque(false);
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
        	this.repaint();
        }
    }
	
}
