package dmme.kuvid.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class gammaMoleculeUI extends MoleculeUI implements PropertyListener{
	
	private static int L=KUVidGame.getInstance().getL();
	
	private GameObject mol;
	private GamePanel panel;
	private Dimension dim;
	private int id;
	
	public gammaMoleculeUI(GameObject mol, GamePanel panel2) {
		super(IconImporter.getIconFromFileName("gamma-.png","molecules",new Dimension((int) (10 * L), (int) (10 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (10 * L));
        this.setSize(dimension);
        
        mol.addPropertyListener("active",this);
        mol.addPropertyListener("position",this);
        this.mol=mol;
        this.panel=panel2;
        this.dim=dimension;
        Random rand= new Random();
        this.id=rand.nextInt(20);
        
	}
	
	
	@Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("active")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
        	
        	Insets insets = this.panel.getInsets();
        	
        	this.panel.add(this);
        	int x=this.mol.getPosition().getX();
        	int y=this.mol.getPosition().getY();
        	//this.setBounds( x+ insets.left, y + insets.top,
             //       this.dim.width, this.dim.height);
            //this.panel.revalidate();
           // this.panel.repaint();
            //System.out.println("I am here");
        }else if (e.getPropertyName().equals("position")) {
        	this.setLocation(this.mol.getPosition().getX(),this.mol.getPosition().getY());
        	Insets insets = this.panel.getInsets();
        	int x=this.mol.getPosition().getX();
        	int y=this.mol.getPosition().getY();
        	//this.setBounds( x+ insets.left, y + insets.top,
             //       this.dim.width, this.dim.height);
        	//this.panel.revalidate();
            //this.panel.repaint();
        	//System.out.println("pos change");
        	//System.out.println("ins:"+this.id+" "+this.mol.getPosition().getY()+""+KUVidGame.getInstance().getPlayableArea());
        }
    }
	
}
