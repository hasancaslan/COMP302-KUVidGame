package dmme.kuvid.ui;

import dmme.kuvid.Application;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.ui.menu.MenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class GameFrame extends JFrame {
    private final ShooterUI shooterUI;
    public GamePanel gamePanel;

    public GameFrame() {
        this(KUVidGame.getInstance().getScreenSize());
    }

    public GameFrame(Dimension size) {
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./assets/kuvid_bc.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        
        this.gamePanel = new GamePanel(KUVidGame.getInstance(), this);
        this.shooterUI = new ShooterUI(KUVidGame.getInstance().getShooter(), gamePanel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.9;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        getContentPane().add(gamePanel, constraints);

        JPanel menuPanel = new MenuPanel();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        getContentPane().add(menuPanel, constraints);

        this.setSize(size);
        this.setTitle("KUVid Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        validate();
        
        

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                char key = e.getKeyChar();
                switch (key) {
                    case 'a':
                        KUVidGame.getInstance().moveShooter(-10);
                        break;
                    case 's':
                        KUVidGame.getInstance().aimShooter(-20);//check
                        break;
                    case 'd':
                        KUVidGame.getInstance().moveShooter(10);
                        break;
                    case 'w':
                        KUVidGame.getInstance().aimShooter(20);//check
                        break;
                    case 'c':
                    	KUVidGame.getInstance().selectAtom();
                    case 38:
                    	System.out.println("Shoot");
                    	KUVidGame.getInstance().shoot();
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
