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
import dmme.kuvid.domain.GameObjects.Atoms.BetaAtom;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class betaAtomUI extends AtomUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	private GameObject atom;
	
	private GamePanel panel;
	
	public betaAtomUI(GameObject atom, GamePanel panel) {
		super(IconImporter.getIconFromFileName("beta.png","atoms",new Dimension((int) (10 * L), (int) (10 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (10 * L));
        this.setSize(dimension);
        this.setLocation(atom.getPosition().getX(),atom.getPosition().getY());
        atom.addPropertyListener("active",this);
        atom.addPropertyListener("postion",this);
        this.atom=atom;
        this.panel=panel;
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.atom.getPosition().getX(),580-10*L);
        	if((boolean) e.getNewValue()) {
        		this.panel.add(this);
        		System.out.println("betaA");
        	}else {
        		this.panel.remove(this);
        		System.out.println("betaB");
        	}
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.atom.getPosition().getX(),this.atom.getPosition().getY());
        }
    }
	
}
