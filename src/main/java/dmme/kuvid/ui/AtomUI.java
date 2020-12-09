package dmme.kuvid.ui;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.Random;

import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.lib.types.*;

public class AtomUI extends Drawable{
	
	protected AtomType type;
	static int number;

/*		LAZIM MI BIR BAKALIM? UI direk Atom Domain objesi instancesine
 * 		sahip olursa gerekemez gibi ***** ATOM UI objesi Domaine mesaj gondermiyor shooter aksine
 * 
	public AtomUI(){
		Atom.addPropertyListener("location", this);
	}
*/
	public AtomType getType() {
		return type;
	}

	private void setType(AtomType type) {
		this.type = type;
	}

	public static int getNumber() {
		return number;
	}

	private static void setNumber(int number) {
		AtomUI.number = number;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}
/*
	@Override
	public void doAction() {
		// TODO Auto-generated method stub		
	}
	

	@Override
	public void Collide() {
		// TODO Auto-generated method stub
		
	}
*/
}
