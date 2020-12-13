package dmme.kuvid.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.utils.IconImporter;

public class PauseWindow extends JFrame{
	

	public PauseWindow() {
        this.setTitle("KUVid");
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        this.setSize(350,200);
        this.setBackground(Color.WHITE);
        ImageIcon Icon = IconImporter.getIconFromFileName("kuvid.png", "", new Dimension(300,150));

        this.setLayout(new FlowLayout());
        this.add(new JLabel(Icon));
        this.add(new JLabel("PAUSED"));
        
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                char key = e.getKeyChar();
                switch (key) {
                    case 'r':
                    	dispose();
                    	KUVidGame.getInstance().resumeGame();
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
