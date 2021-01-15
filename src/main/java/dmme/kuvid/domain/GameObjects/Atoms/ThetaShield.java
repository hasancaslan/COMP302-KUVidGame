package dmme.kuvid.domain.GameObjects.Atoms;

import java.util.Random;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public class ThetaShield extends ShieldDecorator{
    //private Atom toBeDecorated ;


    public ThetaShield(Atom atom){
        this.decoratedAtom = atom;
        this.boost = (new Random().nextInt(11)+5)/100;
        this.paceFactor=atom.paceFactor*(1-0.09);
        this.decoratedAtom.efficiency=atom.getStability();
    }


    @Override
    public double getStability() {
        double new_eff = (1 - this.decoratedAtom.efficiency) * this.boost;

        this.decoratedAtom.efficiency*=(1+new_eff);//TODO changed here with 1+
        return this.decoratedAtom.efficiency;

    }

}
