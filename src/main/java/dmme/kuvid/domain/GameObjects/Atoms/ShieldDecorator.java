package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public abstract class ShieldDecorator extends Atom{
    public Atom decoratedAtom;
    public double boost;



    //TODO
    public ShieldDecorator() {
    }

    @Override
    public void Collusion() { }

    @Override
    public void setPosition(Position position) { 
    	this.decoratedAtom.setPosition(position); 
    	}

    @Override
    public void setAtomType() {
    	this.decoratedAtom.setAtomType();
    }

    public Position getPosition() { 
    	return this.decoratedAtom.getPosition(); 
    }
    
    public void setActive(boolean active) {
    	this.decoratedAtom.setActive(active);
    }
    
    public ObjectType getType() {
    	return this.decoratedAtom.getType();
    }
    
    public Enum getSubType() {
    	return this.decoratedAtom.getSubType();
    }
    
    public Position getDirection() {
    	return this.decoratedAtom.getDirection();
    }
    
    public void setDirection(Position direct) {
    	this.decoratedAtom.setDirection(direct);
    }
    
    public double getPace() {
    	return this.paceFactor;
    }
    
    
    @Override
    public void initStabilityFeatures() { }



    //public abstract Atom addShield(Atom atom);
    public abstract double getStability();

}
