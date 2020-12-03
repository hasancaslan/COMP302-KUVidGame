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


    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("position")) {
            this.location = (double) e.getNewValue();
            animationQueue.addLast(new ShooterAnimation(this, (double) e.getOldValue(), (double) e.getNewValue(), ShooterAnimationType.LOCATION));
        } else if (e.getPropertyName().equals("angle")) {
            this.angle = (double) e.getNewValue();
            animationQueue.addLast(new ShooterAnimation(this, (double) e.getOldValue(), (double) e.getNewValue(), ShooterAnimationType.ANGLE));
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equals("angle")) {
            double deltaD=Math.toRadians((int)evt.getNewValue()-(int)evt.getOldValue());
            //System.out.println("angle change: "+deltaD+" "+evt.getOldValue()+" "+evt.getNewValue());
            BufferedImage rotated = rotate(img,deltaD);
    		img=rotated;
        }
		
	}

	public void changeAngle(double from, double to, double progress) {
		// TODO Auto-generated method stub
		
	}

	public void changeLocation(double from, double to, double progress) {
		// TODO Auto-generated method stub
		
	}
}
