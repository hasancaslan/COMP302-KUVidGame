package dmme.kuvid.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class alphaMoleculeUI extends MoleculeUI{
	
	public alphaMoleculeUI(boolean linearity) {
		super();
//		this.type = ALPHA;
		this.x=super.x;
		this.y=super.y;
		try{
			if(linearity) {
	        	img = ImageIO.read(new File("./assets/molecules/alpha-2.png"));
			}else {
	        	img = ImageIO.read(new File("./assets/molecules/alpha-1.png"));
			}
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
