package dmme.kuvid.ui;

import dmme.kuvid.constants.Config;

import dmme.kuvid.domain.GameObjects.shooter.Shooter;
import dmme.kuvid.ui.animations.Animatable;
import dmme.kuvid.ui.animations.Animation;
import dmme.kuvid.ui.animations.ShooterAnimation;
import dmme.kuvid.ui.animations.ShooterAnimationType;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ShooterUI extends JLabel implements PropertyListener, Animatable {
    private static final double L = 50;
    private double location;
    private double angle;
    private LinkedList<Animation> animationQueue;

    public ShooterUI(Shooter shooter) {
        super(getIconFromFileName(new Dimension((int) (0.5 * L), (int) L)));
        Dimension dimension = new Dimension((int) (0.5 * L), (int) L);
        this.setSize(dimension);
        this.setLocation(0, 0);
        this.setVisible(true);
        shooter.addPropertyListener("location", this);
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

    public void changeLocation(double from, double to, double progress) {

    }

    public void changeAngle(double from, double to, double progress) {

    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {
        if (e.getPropertyName().equals("location")) {
            this.location = (double) e.getNewValue();
            animationQueue.addLast(new ShooterAnimation(this, (double) e.getOldValue(), (double) e.getNewValue(), ShooterAnimationType.LOCATION));
        } else if (e.getPropertyName().equals("angle")) {
            this.angle = (double) e.getNewValue();
            animationQueue.addLast(new ShooterAnimation(this, (double) e.getOldValue(), (double) e.getNewValue(), ShooterAnimationType.ANGLE));
        }
    }

    @Override
    public LinkedList<Animation> getAnimationQueue() {
        return animationQueue;
    }
}
