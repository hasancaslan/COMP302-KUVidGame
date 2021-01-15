package dmme.kuvid.ui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.PowerType;
import dmme.kuvid.lib.types.ShieldType;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

public class ShieldPanel extends JPanel implements PropertyListener{
	 private final JLabel etaCountLabel;
	    private final JLabel lotaCountLabel;
	    private final JLabel thetaCountLabel;
	    private final JLabel zetaCountLabel;
	   
	    public ShieldPanel() {
	    super(new GridLayout(4, 2));
        setOpaque(true);
        this.setBackground(new Color(204, 230, 255));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0, 0, 0, 120)));

        //Font defaultFont = new Font("Sans-Serif", Font.PLAIN, Config.fontSize);
        Font defaultFont = new Font("Sans-Serif", Font.PLAIN, Config.fontSize);

        //////////////////////////////// shields start

        etaCountLabel = new JLabel();
        lotaCountLabel = new JLabel();
        thetaCountLabel = new JLabel();
        zetaCountLabel = new JLabel();

        etaCountLabel.setFont(defaultFont);
        lotaCountLabel.setFont(defaultFont);
        thetaCountLabel.setFont(defaultFont);
        zetaCountLabel.setFont(defaultFont);


        ImageIcon eta = IconImporter.getIconFromFileName("eta.png", "shields", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon lota = IconImporter.getIconFromFileName("iota.png", "shields", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon theta = IconImporter.getIconFromFileName("theta.png", "shields", new Dimension(Config.fontSize,Config.fontSize));
        ImageIcon zeta = IconImporter.getIconFromFileName("zeta.png", "shields", new Dimension(Config.fontSize, Config.fontSize));

        JLabel etaIcon = new JLabel(eta,JLabel.TRAILING);
        JLabel lotaIcon = new JLabel(lota,JLabel.TRAILING);
        JLabel thetaIcon = new JLabel(theta,JLabel.TRAILING);
        JLabel zetaIcon = new JLabel(zeta,JLabel.TRAILING);


        etaIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                KUVidGame.getInstance().selectShield(ShieldType.ETA);
            }
        });

        lotaIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                KUVidGame.getInstance().selectShield(ShieldType.LOTA);
            }
        });

        thetaIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                KUVidGame.getInstance().selectShield(ShieldType.THETA);
            }
        });

        zetaIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                KUVidGame.getInstance().selectShield(ShieldType.ZETA);
            }
        });

        this.add(etaIcon);
        this.add(etaCountLabel);
        this.add(lotaIcon);
        this.add(lotaCountLabel);
        this.add(thetaIcon);
        this.add(thetaCountLabel);
        this.add(zetaIcon);
        this.add(zetaCountLabel);

        //////////////////////////////// shields end
	    }
	    
	    public void setShieldCount(ShieldType type, int count) {
	        switch (type) {
	            case ETA:
	                etaCountLabel.setText("  " + count);
	                break;
	            case LOTA:
	                lotaCountLabel.setText("  " + count);
	                break;
	            case THETA:
	                thetaCountLabel.setText("  " + count);
	                break;
	            case ZETA:
	                zetaCountLabel.setText("  " + count);
	                break;
	        }
	    }


	    public void updateShieldCounts() {
	    	 for (ShieldType type : ShieldType.values()) {
	    		 setShieldCount(type, KUVidGame.getInstance().getShieldNum(type));
	         }
	    }
	    
		@Override
		public void onPropertyEvent(PropertyEvent e) {
			// TODO Auto-generated method stub
			
		}

}
