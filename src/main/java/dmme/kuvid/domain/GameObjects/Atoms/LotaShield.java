package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public class LotaShield extends ShieldDecorator{
    //private Atom toBeDecorated ;


    public LotaShield(Atom atom){
        this.decoratedAtom = atom;this.boost=0.1;this.paceFactor*=(1-0.07);
    }


    @Override
    public double getStability() {
        double new_eff = (1 - this.decoratedAtom.efficiency) * this.boost;

        return this.decoratedAtom.efficiency*=new_eff;

    }


}
