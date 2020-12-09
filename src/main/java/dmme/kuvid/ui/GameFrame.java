package dmme.kuvid.ui;

import dmme.kuvid.domain.KUVidGame;

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

    public GameFrame(Dimension size) {
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./assets/kuvid_bc.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getContentPane().setLayout(new GridBagLayout()); //setting layout of main frame
        GridBagConstraints cns = new GridBagConstraints(); //creating constraint

        this.shooterUI = new ShooterUI(KUVidGame.getInstance().getShooter());
        this.gamePanel = new GamePanel(KUVidGame.getInstance(), this.shooterUI, this);

        cns.gridx = 0;
        cns.gridy = 1;
        cns.weightx = 0.8;
        cns.weighty = 1;
        cns.anchor = GridBagConstraints.PAGE_START;
        cns.fill = GridBagConstraints.BOTH;
        getContentPane().add(gamePanel, cns);

        JPanel menuPanel = new JPanel();
        cns.gridx = 1;
        cns.gridy = 1;
        cns.weightx = 0.2;
        cns.weighty = 1;
        cns.anchor = GridBagConstraints.FIRST_LINE_START;
        cns.fill = GridBagConstraints.BOTH;
        menuPanel.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0, 0, 0, 120)));
        menuPanel.setBackground(new Color(0, 0, 0, 80));
        getContentPane().add(menuPanel, cns);


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
