package dmme.kuvid.ui;

import dmme.kuvid.domain.KUVidGame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameWindow extends JFrame {
    public Units units;
    private final ShooterUI shooterUI;
    private int h;
    private int w;

    public GameWindow() {
        this.shooterUI = new ShooterUI(KUVidGame.getInstance().getShooter());
        this.units = new Units(this.shooterUI);
        this.add(units);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                char key = e.getKeyChar();
                switch (key) {
                    case 'a':
                        KUVidGame.getInstance().moveShooter(-10);
                        break;
                    case 's':
                        KUVidGame.getInstance().aimShooter(-10);
                        break;
                    case 'd':
                        KUVidGame.getInstance().moveShooter(10);
                        break;
                    case 'w':
                        KUVidGame.getInstance().aimShooter(10);
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
