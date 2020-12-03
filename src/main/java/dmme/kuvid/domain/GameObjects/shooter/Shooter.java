package dmme.kuvid.domain.GameObjects.shooter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import dmme.kuvid.lib.types.AmmoType;
import dmme.kuvid.utils.observer.Observable;

public class Shooter extends Observable {
    private int position;
    private int angle;
    public AmmoType ammoType;
    protected PropertyChangeSupport propertyChangeSupport;
   

    public Shooter() {
    	this.position=0;
    	this.angle=90;
    	this.ammoType=null;
    	propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    public Shooter(int position, int angle, AmmoType ammoType) {
        this.position = position;
        this.angle = angle;
        this.ammoType = ammoType;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        publishPropertyEvent("position", this.position, position);
        this.position = position;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        propertyChangeSupport.firePropertyChange("angle",this.angle, angle);
        this.angle = angle;
        
    }

    public AmmoType getAmmoType() {
        return ammoType;
    }

    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
