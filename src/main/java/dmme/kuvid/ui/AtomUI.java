package dmme.kuvid.ui;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.lib.types.*;

public class AtomUI extends JLabel{
	
	protected AtomType type;
	static int number;
	
	public AtomUI(ImageIcon imageIcon) {
		super(imageIcon);
	}

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

}
