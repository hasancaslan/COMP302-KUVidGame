package dmme.kuvid.ui;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Atoms.AlphaAtom;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class alphaAtomUI extends AtomUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	private GameObject atom;
	
	
	public alphaAtomUI(GameObject atom) {
		super(IconImporter.getIconFromFileName("alpha.png","atoms",new Dimension((int) (0.1 * L), (int) (0.1 * L))));
        Dimension dimension = new Dimension((int) (0.1 * L), (int) (0.1 * L));
        this.setSize(dimension);
        atom.addPropertyListener("active",this);

	
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.atom.getPosition().getX(),this.atom.getPosition().getY());
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.atom.getPosition().getX(),this.atom.getPosition().getY());
        }
    }


}
