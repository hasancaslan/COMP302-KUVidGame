package dmme.kuvid.ui.menu;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.utils.IconImporter;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;

import javax.swing.*;
import java.awt.*;

public class BlenderPanel extends JPanel implements PropertyListener {
    private final JLabel alphaCountLabel;
    private final JLabel betaCountLabel;
    private final JLabel gammaCountLabel;
    private final JLabel sigmaCountLabel;

    public BlenderPanel() {
        super(new GridLayout(5, 2));
        setOpaque(true);
        this.setBackground(new Color(204, 230, 255));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0, 0, 0, 120)));

        Font defaultFont = new Font("Sans-Serif", Font.PLAIN, Config.fontSize);

        alphaCountLabel = new JLabel();
        betaCountLabel = new JLabel();
        gammaCountLabel = new JLabel();
        sigmaCountLabel = new JLabel();

        alphaCountLabel.setFont(defaultFont);
        betaCountLabel.setFont(defaultFont);
        gammaCountLabel.setFont(defaultFont);
        sigmaCountLabel.setFont(defaultFont);

        updateAtomCounts();

        ImageIcon alphaIcon = IconImporter.getIconFromFileName("alpha.png", "atoms", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon betaIcon = IconImporter.getIconFromFileName("beta.png", "atoms", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon gammaIcon = IconImporter.getIconFromFileName("gamma.png", "atoms", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon sigmaIcon = IconImporter.getIconFromFileName("sigma.png", "atoms", new Dimension(Config.fontSize, Config.fontSize));
        ImageIcon blenderIcon = IconImporter.getIconFromFileName("blender.png", "", new Dimension(Config.fontSize * 2, Config.fontSize * 2));

        JLabel alphaIconLabel = new JLabel(alphaIcon, JLabel.TRAILING);
        JLabel betaIconLabel = new JLabel(betaIcon, JLabel.TRAILING);
        JLabel gammaIconLabel = new JLabel(gammaIcon, JLabel.TRAILING);
        JLabel sigmaIconLabel = new JLabel(sigmaIcon, JLabel.TRAILING);
        JLabel blenderIconLabel = new JLabel(blenderIcon, JLabel.LEADING);

        this.add(new JLabel());
        this.add(blenderIconLabel);
        this.add(alphaIconLabel);
        this.add(alphaCountLabel);
        this.add(betaIconLabel);
        this.add(betaCountLabel);
        this.add(gammaIconLabel);
        this.add(gammaCountLabel);
        this.add(sigmaIconLabel);
        this.add(sigmaCountLabel);
    }

    public void updateAtomCounts() {
        for (AtomType type : AtomType.values()) {
            setAtomCount(type, KUVidGame.getInstance().getNumAtom(type));
        }
    }

    private void setAtomCount(AtomType type, int count) {
        switch (type) {
            case ALPHA:
                alphaCountLabel.setText("  " + count);
                break;
            case BETA:
                betaCountLabel.setText("  " + count);
                break;
            case GAMMA:
                gammaCountLabel.setText("  " + count);
                break;
            case SIGMA:
                sigmaCountLabel.setText("  " + count);
                break;
        }
    }

    @Override
    public void onPropertyEvent(PropertyEvent e) {

    }
}
