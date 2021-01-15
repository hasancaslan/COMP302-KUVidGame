package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

import java.util.Random;

public class ZetaShield extends ShieldDecorator{
    //private Atom toBeDecorated ;

    public ZetaShield(Atom atom){
        this.decoratedAtom = atom;
        this.boost = 0.2;
        this.paceFactor=atom.paceFactor*(1-0.11);
        this.decoratedAtom.efficiency=atom.getStability();
    }




    @Override
    public double getStability() {
        double new_eff = (1 - this.decoratedAtom.efficiency) * this.boost;
        
        if(this.decoratedAtom.neutrons==this.decoratedAtom.protons) {
        	this.decoratedAtom.efficiency*=(1+new_eff);//TODO changed here with 1+
        }
        return this.decoratedAtom.efficiency;

    }

}
