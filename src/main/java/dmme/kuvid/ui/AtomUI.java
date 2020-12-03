package dmme.kuvid.ui;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.Random;

import dmme.kuvid.lib.types.*;

public class AtomUI extends Drawable{
	
	protected AtomType type;
	static int number;


    /*
	public AtomUI(){
		Random rand = new Random();
		x = 40*rand.nextInt(10);
		y = 40*rand.nextInt(10);
		while(x==200){
			x = 40*rand.nextInt(10);
		}
		while(y==200){
			y = 40*rand.nextInt(10);
		}
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

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Collide() {
		// TODO Auto-generated method stub
		
	}
}
