package dmme.kuvid.domain.GameObjects.Atoms;

import dmme.kuvid.domain.GameObjects.Position;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.ObjectType;

public class EtaShield extends ShieldDecorator{
    //Atom decoratedAtom ;


    public EtaShield(Atom atom){
        this.decoratedAtom = atom;
        this.boost=0.05;
        //System.out.println("incoming atom pacefactor: "+atom.paceFactor);
        //this.decoratedAtom.paceFactor=atom.paceFactor*(1-0.05);
        this.paceFactor= atom.paceFactor*(1-0.05);
        this.decoratedAtom.efficiency=atom.getStability(); //TODO added line
    }


    @Override
    public double getStability() {
        double new_eff = decoratedAtom.neutrons != decoratedAtom.protons ?
                (1-decoratedAtom.efficiency)* Math.abs(decoratedAtom.neutrons-decoratedAtom.protons)/decoratedAtom.protons
                :(1-decoratedAtom.efficiency)*this.boost;

        //System.out.println(this.decoratedAtom.efficiency);
        //System.out.println(1+new_eff);

        this.decoratedAtom.efficiency*=(1+new_eff);//TODO changed here with 1+
        return this.decoratedAtom.efficiency; //TODO seperated line

    }
    


}
