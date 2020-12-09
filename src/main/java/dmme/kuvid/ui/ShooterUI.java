package dmme.kuvid.ui;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ShooterUI extends Drawable implements PropertyListener {
    private int location;
    private int angle;
    private Shooter shooter;
    private Graphics2D g;

    public ShooterUI(Shooter shooter) {
        this.shooter = shooter;
        try {
            img = ImageIO.read(new File(Config.getAssetsPath() + "shooter.png"));
            img = resize(img, L, 3 * L);
        } catch (IOException e) {
            System.out.printf("% background s", e.getMessage());
        }
        shooter.addPropertyListener("location", this);
        shooter.addPropertyListener("angle", this);
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        //BufferedImage rotated = rotate(img, (double) this.shooter.getAngle());
        //img=rotated;
        g.drawImage(img, this.shooter.getPosition(), (L * N - 3 * L) - 20, null);//check this Y
    }

    @Override
    public void doAction() {
        // TODO Auto-generated method stub

    }

    @Override
    public void Collide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("position")) {
            // this.location = (double) e.getNewValue();
            // animationQueue.addLast(new ShooterAnimation(this, (double) e.getOldValue(), (double) e.getNewValue(), ShooterAnimationType.LOCATION));
        } else if (e.getPropertyName().equals("angle")) {
            double deltaD = Math.toRadians((int) e.getNewValue() - (int) e.getOldValue());
            img = rotate(img, deltaD);
        }
    }

    public void changeAngle(double from, double to, double progress) {
        // TODO Auto-generated method stub

    }

    public void changeLocation(double from, double to, double progress) {
        // TODO Auto-generated method stub

    }
}
