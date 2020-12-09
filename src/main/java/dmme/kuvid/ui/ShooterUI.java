package dmme.kuvid.ui;

import dmme.kuvid.constants.Config;

import dmme.kuvid.domain.GameObjects.shooter.Shooter;
import dmme.kuvid.ui.animations.Animatable;
import dmme.kuvid.ui.animations.Animation;
import dmme.kuvid.ui.animations.ShooterAnimation;
import dmme.kuvid.ui.animations.ShooterAnimationType;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ShooterUI extends Drawable implements PropertyChangeListener, Animatable {
    private int location;
    private int angle;
    private LinkedList<Animation> animationQueue;
    private Shooter shooter;
    private Graphics2D g;

    public ShooterUI(Shooter shooter) {
    	this.shooter=shooter;
        try{							
        	img = ImageIO.read(new File(Config.getAssetsPath() + "/shooter.png"));
        	BufferedImage resized = resize(img, L, 3*L);
        	img = resized;
        } catch(IOException e) {
        System.out.printf("% background s",e.getMessage());
        }
       this.shooter.addPropertyChangeListener(this);
       this.g=img.createGraphics();
    }

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		//BufferedImage rotated = rotate(img, (double) this.shooter.getAngle());
		//img=rotated;
		g.drawImage(img,this.shooter.getPosition(),(L*N-3*L)-20,null);//check this Y
	
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Collide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList<Animation> getAnimationQueue() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equals("angle")) {
            double deltaD=(int)evt.getNewValue()-(int)evt.getOldValue();
            //System.out.println("angle change: "+deltaD+" "+evt.getOldValue()+" "+evt.getNewValue());
            BufferedImage rotated = rotate(img,Math.toRadians(deltaD));
    		img=rotated;
    	
    		  // the actual location of the sprite
    		
        }	
	}

	public void changeAngle(double from, double to, double progress) {
		// TODO Auto-generated method stub
		
	}

	public void changeLocation(double from, double to, double progress) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Graphics2D getGraphics() {
		return g;
	}
}
