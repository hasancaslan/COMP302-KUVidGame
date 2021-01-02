package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.ObjectType;

public class EtaShield extends ShieldDecorator{
    //private Atom toBeDecorated ; TODO redundant?


    public EtaShield(Atom atom){
        this.decoratedAtom = atom;this.boost=0.05;this.paceFactor*=(1-0.05);
    }

    @Override
    public double getStability() {
        double new_eff = decoratedAtom.neutrons != decoratedAtom.protons ?
                (1-decoratedAtom.efficiency)* Math.abs(decoratedAtom.neutrons-decoratedAtom.protons)/decoratedAtom.protons
                :(1-decoratedAtom.efficiency)*this.boost;

        return this.decoratedAtom.efficiency*=new_eff;

    }


    /*
    @Override
    public double addShield() {
        double new_eff = decoratedAtom.neutrons != decoratedAtom.protons ?
                (1-decoratedAtom.efficiency)* Math.abs(decoratedAtom.neutrons-decoratedAtom.protons)/decoratedAtom.protons
                :(1-decoratedAtom.efficiency)*this.boost;

        return this.decoratedAtom.efficiency*=new_eff;

    }

     */
}
