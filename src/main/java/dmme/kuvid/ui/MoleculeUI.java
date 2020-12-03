package dmme.kuvid.ui;

import java.awt.Graphics;


import dmme.kuvid.lib.types.*;

public class MoleculeUI extends Drawable{

	
	protected MoleculeType type;
	static int number;
    
	public MoleculeType getType() {
		return type;
	}

	private void setType(MoleculeType type) {
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

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Collide() {
		// TODO Auto-generated method stub
		
	}
}
