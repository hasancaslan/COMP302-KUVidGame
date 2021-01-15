package dmme.kuvid.ui.menu;

import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements PropertyListener {
    ScorePanel scorePanel;
    PowerUpPanel powerUpPanel;
    ShieldPanel shieldPanel;
    BlenderPanel blenderPanel;

    public MenuPanel() {
        this.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(0, 0, 0, 120)));
        this.setBackground(new Color(0, 0, 0, 100));

        scorePanel = new ScorePanel();
        powerUpPanel = new PowerUpPanel();
        shieldPanel = new ShieldPanel();
        blenderPanel = new BlenderPanel();

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0.25;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(scorePanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 0.25;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(powerUpPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 0.25;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(shieldPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 1;
        constraints.weighty = 0.25;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(blenderPanel, constraints);
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }
    
    public BlenderPanel getBlenderPanel() {
    	return this.blenderPanel;
    }
    
    public PowerUpPanel getPowerUpPanel() {
    	return this.powerUpPanel;
    }
    
    public ShieldPanel getShieldPanel() {
    	return this.shieldPanel;
    }
}
