package dmme.kuvid.ui;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import javax.swing.JFrame;

import dmme.kuvid.domain.KUVidGame;


public class GameWindow  extends JFrame{
    public Units units;
    private ShooterUI shooterUI;
    private KUVidGame game;


public GameWindow(KUVidGame game){
	
	this.game=game;
	
	this.shooterUI=new ShooterUI(this.game.getShooter());
	this.units=new Units(this.game,this.shooterUI);
	this.add(units);
    this.addKeyListener(new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {

        	char key=e.getKeyChar();
            switch(key) {
                case 'a':
                	GameWindow.this.game.moveShooter(-10);
                    break;
                case 's':
                	GameWindow.this.game.aimShooter(-10);
                    break;
                case 'd':
                	GameWindow.this.game.moveShooter(10);
                    break;
                case 'w':
                	GameWindow.this.game.aimShooter(10);
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
