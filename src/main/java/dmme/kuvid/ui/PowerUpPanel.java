package dmme.kuvid.ui;

import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerUpPanel extends JPanel implements ActionListener, PropertyListener {

    public PowerUpPanel() {
        setOpaque(false);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0, 0, 0, 120)));
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
