package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public class ThetaShield extends ShieldDecorator{
    //private Atom toBeDecorated ;


    public ThetaShield(Atom atom){
        this.decoratedAtom = atom;this.boost = 0.2;this.paceFactor*=(1-0.09);
    }


    @Override
    public double getStability() {
        double new_eff = this.protons == this.neutrons ?
                (1 - this.decoratedAtom.efficiency) * this.boost
                :1;

        return this.decoratedAtom.efficiency*=new_eff;

    }

}
