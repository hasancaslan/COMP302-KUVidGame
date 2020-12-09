package dmme.kuvid.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import dmme.kuvid.constants.Config;

public class sigmaAtomUI extends AtomUI{
	
	public sigmaAtomUI() {
		super();
//		this.type = ALPHA;
		this.x=super.x;
		this.y=super.y;
		try{							
//        	img = ImageIO.read(new File("./assets/atoms/sigma.png"));
        	img = ImageIO.read(new File(Config.getAssetsPath() + "atoms/sigma.png"));
        	BufferedImage resized = resize(img, L, L);
        	img = resized;
        } catch(IOException e) {
        System.out.printf("%s",e.getMessage());
        }	
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);	
	}
	
}
