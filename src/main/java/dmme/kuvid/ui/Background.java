package dmme.kuvid.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background extends Drawable{
	
	public Background() {
		super();
		this.x=0;
		this.y=0;
		try{							
        	img = ImageIO.read(new File("./assets/kuvid_bc.png"));
        	BufferedImage resized = resize(img, L*N, L*N);
        	img = resized;
        } catch(IOException e) {
        System.out.printf("% background s",e.getMessage());
        }	
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);	
	}
}
