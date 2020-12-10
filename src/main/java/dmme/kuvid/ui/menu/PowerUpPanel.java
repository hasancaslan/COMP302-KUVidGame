package dmme.kuvid.ui.menu;

import dmme.kuvid.constants.Config;
import dmme.kuvid.lib.types.PowerType;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;

public class PowerUpPanel extends JPanel implements PropertyListener {
    private final JLabel alphaBCountLabel;
    private final JLabel betaBCountLabel;
    private final JLabel gammaBCountLabel;
    private final JLabel sigmaBCountLabel;

    public PowerUpPanel() {
        super(new GridLayout(4, 2));
        setOpaque(false);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0, 0, 0, 120)));

        Font defaultFont = new Font("Sans-Serif", Font.PLAIN, Config.fontSize);

        alphaBCountLabel = new JLabel();
        betaBCountLabel = new JLabel();
        gammaBCountLabel = new JLabel();
        sigmaBCountLabel = new JLabel();

        alphaBCountLabel.setFont(defaultFont);
        betaBCountLabel.setFont(defaultFont);
        gammaBCountLabel.setFont(defaultFont);
        sigmaBCountLabel.setFont(defaultFont);

        setPowerUpCount(PowerType.ALPHA_B, 0);
        setPowerUpCount(PowerType.BETA_B, 0);
        setPowerUpCount(PowerType.GAMMA_B, 0);
        setPowerUpCount(PowerType.SIGMA_B, 0);

        ImageIcon alphaBIcon = IconImporter.getIconFromFileName("+alpha-b.png", "powerups", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon betaBIcon = IconImporter.getIconFromFileName("+beta-b.png", "powerups", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon gammaBIcon = IconImporter.getIconFromFileName("+gamma-b.png", "powerups", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon sigmaBIcon = IconImporter.getIconFromFileName("+sigma-b.png", "powerups", new Dimension(Config.fontSize, Config.fontSize));

        JLabel alphaBIconLabel = new JLabel(alphaBIcon, JLabel.TRAILING);
        JLabel betaBIconLabel = new JLabel(betaBIcon, JLabel.TRAILING);
        JLabel gammaBIconLabel = new JLabel(gammaBIcon, JLabel.TRAILING);
        JLabel sigmaBIconLabel = new JLabel(sigmaBIcon, JLabel.TRAILING);

        this.add(alphaBIconLabel);
        this.add(alphaBCountLabel);
        this.add(betaBIconLabel);
        this.add(betaBCountLabel);
        this.add(gammaBIconLabel);
        this.add(gammaBCountLabel);
        this.add(sigmaBIconLabel);
        this.add(sigmaBCountLabel);
    }

    public void setPowerUpCount(PowerType type, int count) {
        switch (type) {
            case ALPHA_B:
                alphaBCountLabel.setText("  " + count);
                break;
            case BETA_B:
                betaBCountLabel.setText("  " + count);
                break;
            case GAMMA_B:
                gammaBCountLabel.setText("  " + count);
                break;
            case SIGMA_B:
                sigmaBCountLabel.setText("  " + count);
                break;
        }
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }
}
