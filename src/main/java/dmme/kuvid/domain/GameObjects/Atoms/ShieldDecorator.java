package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public abstract class ShieldDecorator extends Atom{
    protected Atom decoratedAtom;
    protected double boost;



    //TODO
    protected ShieldDecorator() {
    }

    @Override
    public void Collusion() { }

    @Override
    public void setPosition(Position position) {  }

    @Override
    public void setAtomType() { }




    @Override
    public void initStabilityFeatures() { }


    //
    //public abstract double addShield();
    public abstract double getStability();

}
