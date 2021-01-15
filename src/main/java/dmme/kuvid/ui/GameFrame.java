package dmme.kuvid.ui;

import dmme.kuvid.Application;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.destroyHandler;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.ui.menu.MenuPanel;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class GameFrame extends JFrame implements PropertyListener{
    private ShooterUI shooterUI;
    public GamePanel gamePanel;
    private static MenuPanel menu;

    public GameFrame() {
        this(KUVidGame.getInstance().getScreenSize());
        KUVidGame.getInstance().addPropertyListener("finishGame",this);
        KUVidGame.getInstance().addPropertyListener("load", this);
        destroyHandler.getInstance().addPropertyListener("updateAtom",this);
        DomainFactory.getInstance().addPropertyListener("updateAtom", this);
        DomainFactory.getInstance().addPropertyListener("updatePower", this);
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
       

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.9;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        getContentPane().add(gamePanel, constraints);

        MenuPanel menuPanel = new MenuPanel();
        this.menu=menuPanel;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        getContentPane().add(menuPanel, constraints);

        menuPanel.getBlenderPanel().updateAtomCounts();
        JFrame pause= new PauseWindow();
        
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
                    case 's':
                        KUVidGame.getInstance().aimShooter(-10);//check
                        break;
                    case 'w':
                        KUVidGame.getInstance().aimShooter(10);//check
                        break;
                    case 'c':
                    	KUVidGame.getInstance().selectAtom();
                    	break;
                    case 'p':
                    	KUVidGame.getInstance().pauseGame();
                    	pause.setVisible(true);
                    	break;
                    case 'r':
                    	pause.dispose();
                    	KUVidGame.getInstance().resumeGame();
                    	break;
                    case 'b':
                    	KUVidGame.getInstance().pauseGame();
                    	new BlenderWindow();
                    	break;
                    default:
                    	System.out.println(e.getKeyCode());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            	int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        KUVidGame.getInstance().moveShooter(-10);
                        break;
                    case KeyEvent.VK_RIGHT:
                        KUVidGame.getInstance().moveShooter(10);
                        break;
                    	
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            	int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                    	KUVidGame.getInstance().shoot();
                    	break;
                    	
                }

            }
        });
        
        
    }
    
    public static void updateNumShield() {
    	GameFrame.menu.getPowerUpPanel().updateShieldCounts();
    }

	@Override
	public void onPropertyEvent(PropertyEvent e) {
		// TODO Auto-generated method stub
		if(e.getPropertyName().equals("updateAtom")) {
			GameFrame.menu.getBlenderPanel().updateAtomCounts();
		}else if(e.getPropertyName().equals("updatePower")) {
			GameFrame.menu.getPowerUpPanel().updatePowerCounts();
		}else if(e.getPropertyName().equals("finishGame")) {
			new FinishWindow();
		}else if(e.getPropertyName().equals("load")) {
			 movementHandler.getInstance().addPropertyListener("updatePower",this);
			 KUVidGame.getInstance().getShooter().addPropertyListener("updatePower",this);
		     KUVidGame.getInstance().getShooter().addPropertyListener("updateAtom",this);
		     this.shooterUI = new ShooterUI(KUVidGame.getInstance().getShooter(), gamePanel);
		     this.shooterUI.loadShooter(KUVidGame.getInstance().getShooter());
		}
		
	}
}
