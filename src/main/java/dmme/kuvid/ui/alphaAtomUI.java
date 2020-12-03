package dmme.kuvid.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class alphaAtomUI extends AtomUI{
	
	public alphaAtomUI() {
		super();
//		this.type = ALPHA;
		this.x=super.x;
		this.y=super.y;
		try{							
        	img = ImageIO.read(new File("./assets/atoms/alpha.png"));
        	BufferedImage resized = resize(img, L, L);
        	img = resized;
        } catch(IOException e) {
        System.out.printf("% alpha s",e.getMessage());
        }	
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img,x,y,null);	
	}

	@Override
	public void doAction() {
/*	
			Random rand = new Random();
			move = rand.nextInt(4);
			
			if(move==0 && x+ l <420) {
				x+=speed;
				repaint();
			} else if (move==1 && x>0) {
				x-=speed;
				repaint();
			} else if(move==2 && y>0) {
				y-=speed;
				repaint();
			} else if(move==3 && y+h<420) {
				y+=speed;
				repaint();
			}
*/
	}

	
	
}
