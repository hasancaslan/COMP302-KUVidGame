package dmme.kuvid.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import dmme.kuvid.constants.Config;

public class betaAtomUI extends AtomUI{
	
	public betaAtomUI() {
		super();
//		this.type = ALPHA;
		this.x=super.x;
		this.y=super.y;
		try{							
//        	img = ImageIO.read(new File("./assets/atoms/beta.png"));
        	img = ImageIO.read(new File(Config.getAssetsPath() + "atoms/beta.png"));
        	BufferedImage resized = resize(img, L, L);
        	img = resized;
        } catch(IOException e) {
        System.out.printf("% beta s",e.getMessage());
        }	
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);	
	}
	
}
