package dmme.kuvid.ui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dmme.kuvid.lib.types.*;

public class MoleculeUI extends JLabel{

	
	protected MoleculeType type;
	static int number;
	
	public MoleculeUI(ImageIcon imageIcon) {
		super(imageIcon);
	}
    
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
	
}
