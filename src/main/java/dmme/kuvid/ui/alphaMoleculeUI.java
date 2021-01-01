package dmme.kuvid.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.Molecule;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class alphaMoleculeUI extends MoleculeUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	private static int linearity=KUVidGame.getInstance().getLinearity()+1;
	
	private Molecule mol;
	private GamePanel panel;
	
	public alphaMoleculeUI(GameObject mol, GamePanel panel2) {
		super(IconImporter.getIconFromFileName("0-alpha-" + linearity + ".png","molecules",new Dimension((int) (10 * L), (int) (10 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (10 * L));
        this.setSize(dimension);
        
        mol.addPropertyListener("active",this);
        mol.addPropertyListener("position",this);
        this.mol=(Molecule) mol;
        this.panel=panel2;
	}


/*	
    @Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.shooter.getPosition(),this.y);
        	this.panel.add(this);
        } else if (e.getPropertyName().equals("angle")) {
        	int angle=(this.shooter.getAngle()-90);
        	String s = "shooter"+angle+".png";
        	ImageIcon icon =IconImporter.getIconFromFileName(s,"shooter",new Dimension((int) (10 * L), (int) (20 * L)));
        	this.setIcon(icon);
        	this.panel.add(this);
        }
    }
*/  
		
    
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY()-10*L);
        	if((boolean) e.getNewValue()) {
        		this.panel.add(this);
        	}else {
        		this.panel.remove(this);
        	}
        }else if (e.getPropertyName().equals("position")) {
        	String s = "" + this.mol.getSpin()*90 +"-alpha-" + linearity + ".png";
        	ImageIcon icon =IconImporter.getIconFromFileName(s,"molecules",new Dimension((int) (10 * L), (int) (10 * L)));
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY()-10*L);
         	this.setIcon(icon);
        	this.panel.add(this);
        }
    }
}