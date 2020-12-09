package dmme.kuvid.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Drawable extends JPanel {

    protected static int L = 50;
    protected static int N = 10;
    protected int x;
    protected int y;
    BufferedImage img;

    public static int getL() {
        return L;
    }

    public static void setL(int L) {
        Drawable.L = L;
    }

    public static void setN(int N) {
        Drawable.N = N;
    }

    //This code is taken from the Internet to adjust size of the image
    //https://memorynotfound.com/java-resize-image-fixed-width-height-example/
    public static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    //This code is taken from the Internet to rotate
    //https://memorynotfound.com/java-resize-image-fixed-width-height-example/
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

//	public abstract void doAction();

//	public abstract void Collide();

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void draw(Graphics g);


}
