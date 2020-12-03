package dmme.kuvid.ui;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import dmme.kuvid.domain.KUVidGame;

public class Units extends JPanel {
	
	private int ind;
	private Background map = new Background();
	private KUVidGame game;
	private ShooterUI shooterUI;
	//ShooterUI shooter= new ShooterUI((parameters.getGameSize()-1)/2, parameters.getGameSize()-2);
	
	List<Drawable> list;
//	int atom_number = KUVidGame.getAtoms();
//	int molecule_number = KUVidGame.getMolecules();
	
	//int atom_number = parameters.getAtomNumber();
	//int molecule_number = parameters.getMoleculeNumber();
	
			
	public Units(KUVidGame game, ShooterUI shooter) {

		list = new ArrayList<>();
		list.add(map);
		this.game=game;
		boolean active=this.game.isActive();
		this.shooterUI=shooter;
		list.add(this.shooterUI);
		
		/*for(int i=0; i<atom_number; i++) {
			list.add(new alphaAtomUI());
		}*/
		/*for(int i=0; i<molecule_number; i++) {
			list.add(new alphaMoleculeUI(true));
		}*/
		
		
		Timer timer = new Timer(250, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(active){
				for(Drawable t : list) {
					//t.doAction();
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
	

    

}

