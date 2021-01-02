package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

import java.util.Random;

public class ZetaShield extends ShieldDecorator{
    //private Atom toBeDecorated ;

    public ZetaShield(Atom atom){
        this.decoratedAtom = atom;this.boost = (new Random().nextInt(11)+5)/100;this.paceFactor*=(1-0.11);
    }




    @Override
    public double getStability() {
        double new_eff = (1 - this.decoratedAtom.efficiency) * this.boost;

        return this.decoratedAtom.efficiency*=new_eff;

    }

}
