package dmme.kuvid.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundImage extends Drawable {

    public BackgroundImage() {
        super();
        this.x = 0;
        this.y = 0;
        try {
            img = ImageIO.read(new File("./assets/kuvid_bc.png"));
            BufferedImage resized = resize(img, L * N, L * N);
            img = resized;
        } catch (IOException e) {
            System.out.printf("% background s", e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }


    @Override
    public void doAction() {
    }

    @Override
    public void Collide() {
    }

}
