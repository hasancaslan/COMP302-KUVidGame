package dmme.kuvid.ui;

import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, PropertyListener {
    List<Drawable> list;
    private GameFrame mainFrame;
    private KUVidGame game;
    private ShooterUI shooterUI;


    public GamePanel(KUVidGame game, ShooterUI shooter, GameFrame mainFrame) {
        this.mainFrame = mainFrame;
        list = new ArrayList<>();
        this.game = game;
        boolean active = this.game.isActive();
        this.shooterUI = shooter;
        list.add(this.shooterUI);
        setOpaque(false);
        

        Timer timer = new Timer(250, e -> {
            if (active) {
                movementHandler.getInstance().move();
                for (Drawable t : list) {
                    repaint();
                }
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable t : list) {
            t.draw(g);
        }
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

