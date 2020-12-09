package dmme.kuvid.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import dmme.kuvid.constants.Config;

public class gammaAtomUI extends AtomUI{
	
	public gammaAtomUI() {
		super(getIconFromFileName(new Dimension((int) (0.5 * L), (int) L)));
        //Dimension dimension = new Dimension((int) (0.5 * L), (int) L);
        //this.setSize(dimension);
        this.setLocation(0, 0);
        this.setVisible(true);
        //shooter.addPropertyListener("position", this);

	
	}
	
	public static ImageIcon getIconFromFileName(Dimension shooterDimension) {
        Image tmp = null;
        try {
            tmp = ImageIO.read(new File(Config.getAssetsPath() + "shooter.png"));
            tmp = tmp.getScaledInstance(shooterDimension.width, shooterDimension.height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ImageIcon(tmp);
    }

	

}
