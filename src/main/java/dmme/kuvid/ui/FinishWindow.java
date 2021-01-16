package dmme.kuvid.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.utils.IconImporter;

public class FinishWindow extends JFrame{
	

	public FinishWindow() {
        this.setTitle("KUVid");
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(3);
        
        
        this.setSize(350,200);
        this.setBackground(Color.WHITE);
        ImageIcon Icon = IconImporter.getIconFromFileName("gameOver.png", "", new Dimension(300,150));

        this.setLayout(new FlowLayout());
        this.add(new JLabel(Icon));
        this.add(new JLabel("Your Score: "+Integer.toString((int) (KUVidGame.getInstance().getScore()*100.0))));
        
        this.setVisible(true);
       
 }

}
