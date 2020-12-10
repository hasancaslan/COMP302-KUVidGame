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

public class sigmaMoleculeUI extends MoleculeUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	
	private GameObject mol;
	
	public sigmaMoleculeUI(GameObject mol) {
		super(IconImporter.getIconFromFileName("sigma.png",new Dimension((int) (0.1 * L), (int) (0.1 * L))));
        Dimension dimension = new Dimension((int) (0.1 * L), (int) (0.1 * L));
        this.setSize(dimension);
        
        mol.addPropertyListener("active",this);
        this.mol=mol;
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
            this.setVisible(true);
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
        }
    }
	
}
