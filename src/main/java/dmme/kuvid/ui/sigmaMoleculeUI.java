package dmme.kuvid.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class sigmaMoleculeUI extends MoleculeUI{
	
	public sigmaMoleculeUI() {
		super();
		this.x=super.x;
		this.y=super.y;
		try{
        	img = ImageIO.read(new File("./assets/molecules/sigma.png"));
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
