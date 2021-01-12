package dmme.kuvid.ui;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


import java.awt.*;


public class ShooterUI extends JLabel implements PropertyListener {
    
	private Shooter shooter;
    
    private Dimension dim;
    
    private static int L=KUVidGame.getInstance().getL();
    private GamePanel panel;
    private int y;

    public ShooterUI(Shooter shooter, GamePanel p) {
        super(IconImporter.getIconFromFileName("shooter0.png","shooter",new Dimension((int) (10 * L), (int) (20 * L))));
        Dimension dimension = new Dimension((int) (10 * L), (int) (20 * L));
        this.setSize(dimension);
        
        int y=KUVidGame.getInstance().getPlayableArea().height-dimension.height;
        this.setLocation(shooter.getPosition(),y-L);
        this.shooter=shooter;
    	
        shooter.addPropertyListener("position", this);
        shooter.addPropertyListener("angle", this);
        this.dim=dimension;
        this.panel=p;
        this.panel.add(this);
        this.y=y;
    }

   
    
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



	public void changeAngle(double from, double to, double progress) {
		// TODO Auto-generated method stub
		
	}



	public void changeLocation(double from, double to, double progress) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadShooter(Shooter shoot) {
		this.panel.remove(this);
		this.shooter=shoot;
		int angle=(this.shooter.getAngle()-90);
    	String s = "shooter"+angle+".png";
    	ImageIcon icon =IconImporter.getIconFromFileName(s,"shooter",new Dimension((int) (10 * L), (int) (20 * L)));
    	this.setIcon(icon);
    	this.setLocation(this.shooter.getPosition(),this.y);
    	this.panel.add(this);
		shooter.addPropertyListener("position", this);
        shooter.addPropertyListener("angle", this);
	}

}
