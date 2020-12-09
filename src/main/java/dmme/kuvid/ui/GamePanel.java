package dmme.kuvid.ui;

import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;
import dmme.kuvid.domain.Controllers.movementHandler;

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
        
        int atom_number=  game.getNumMolecules();
        int molecule_number=  1;
        
        
		for(int i=0; i<atom_number; i++) {
			list.add(new alphaAtomUI());
			if(i==atom_number) break;
			list.add(new betaAtomUI());
			if(i==atom_number) break;
			list.add(new sigmaAtomUI());
			if(i==atom_number) break;
			list.add(new gammaAtomUI());
		}
		for(int i=0; i<molecule_number; i++) {
			list.add(new alphaMoleculeUI(true));
			if(i==molecule_number) break;
			list.add(new betaMoleculeUI(true));
			if(i==molecule_number) break;
			list.add(new sigmaMoleculeUI());
			if(i==molecule_number) break;
			list.add(new gammaMoleculeUI());
		}


        Timer timer = new Timer(250, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (active) {
                	movementHandler.getInstance().move();
                    for (Drawable t : list) {
  //                    t.doAction();
                        repaint();
                    }
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

    public void setShooterUI(ShooterUI shooterUI) {
        this.shooterUI = shooterUI;
    }


    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

