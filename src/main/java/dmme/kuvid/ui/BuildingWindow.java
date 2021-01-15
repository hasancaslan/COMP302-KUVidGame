package dmme.kuvid.ui;

import dmme.kuvid.Application;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.buildHandler;
import dmme.kuvid.domain.GameObjects.Molecules.MovementStrategy;
import dmme.kuvid.lib.types.GameLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BuildingWindow extends JFrame {
    private final String DEFAULT_COMPONENT_AMOUNT = "400";
    private final String DEFAULT_BLOCKER_AMOUNT = "40";
    private final String DEFAULT_POWER_AMOUNT = "80";
    private final String DEFAULT_SIZE = "5";
    private final String DEFAULT_SHIELD= "30";
    public boolean spinning = false;    //for Alpha and Beta
    public boolean linear = true;
    String[] Difficulty = new String[]{"Easy", "Medium", "Hard"};
    String difficulty;
    
    
    JTextField AtomNumber = new JTextField(DEFAULT_COMPONENT_AMOUNT, 8);
    JTextField ReactionBlockerNumber = new JTextField(DEFAULT_BLOCKER_AMOUNT, 8);
    JTextField PowerUpNumber = new JTextField(DEFAULT_POWER_AMOUNT, 8);
    JTextField MoleculeNumber = new JTextField(DEFAULT_COMPONENT_AMOUNT, 8);
    JTextField LTextField = new JTextField(DEFAULT_SIZE, 8);
    JTextField ShieldCount = new JTextField(DEFAULT_SHIELD,8);
    ButtonGroup spinGroup;
    ButtonGroup movementGroup;
    JRadioButton SpinButton;
    JRadioButton StationaryButton;
    JRadioButton LinearButton;
    JRadioButton NoNLinearButton;
    JComboBox<String> ComboBox;
    JButton StartButton;
    int atomNumber = 0;
    int reactionBlockerNumber = 0;
    int powerUpNumber = 0;
    int moleculeNumber = 0;
    int L = 0;
    int shieldNum=0;

    private JButton Load;
    
    public BuildingWindow() {
        this.setTitle("BUILDING WINDOW");
        this.setSize(510, 510);
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);

        this.ComboBox = new JComboBox<>(this.Difficulty);
        this.StartButton = new JButton("Start Game");
        this.setLayout(new GridLayout(10, 2, 4, 4));

        this.add(new JLabel("Number of Atoms: "));
        this.add(this.AtomNumber);
        this.add(new JLabel("Number of ReactionBlockers: "));
        this.add(this.ReactionBlockerNumber);
        this.add(new JLabel("Number of PowerUps: "));
        this.add(this.PowerUpNumber);
        this.add(new JLabel("Number of Molecules: "));
        this.add(this.MoleculeNumber);
        this.add(new JLabel("Number of Shields: "));
        this.add(this.ShieldCount);

        SpinButton = new JRadioButton("Spinning Molecules", false);
        StationaryButton = new JRadioButton("Stationary Molecules", true);
        LinearButton = new JRadioButton("Linear Structure", true);
        NoNLinearButton = new JRadioButton("Nonlinear Structure", false);

        this.add(this.StationaryButton);
        this.add(this.SpinButton);
        this.add(this.LinearButton);
        this.add(this.NoNLinearButton);

        spinGroup = new ButtonGroup();
        spinGroup.add(StationaryButton);
        spinGroup.add(SpinButton);

        movementGroup = new ButtonGroup();
        movementGroup.add(LinearButton);
        movementGroup.add(NoNLinearButton);

        this.add(new JLabel("Default Distance L ratio: "));
        this.add(this.LTextField);

        this.add(new JLabel("GameDifficulty"));
        this.add(this.ComboBox);
 
        this.add(this.StartButton);
        this.Load=new JButton("Load Game");
        this.add(this.Load);

        this.StartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                atomNumber = Integer.parseInt((String) AtomNumber.getText());
                reactionBlockerNumber = Integer.parseInt((String) ReactionBlockerNumber.getText());
                powerUpNumber = Integer.parseInt((String) PowerUpNumber.getText());
                moleculeNumber = Integer.parseInt((String) MoleculeNumber.getText());
                L = Integer.parseInt((String) LTextField.getText());
                difficulty = ComboBox.getItemAt(ComboBox.getSelectedIndex());
                shieldNum = Integer.parseInt((String) ShieldCount.getText());

                buildHandler.getInstance().setNumAtoms(atomNumber);
                buildHandler.getInstance().setNumMolecules(moleculeNumber);
                buildHandler.getInstance().setNumBlocker(reactionBlockerNumber);
                buildHandler.getInstance().setNumPowerUp(powerUpNumber);
                buildHandler.getInstance().setL(L);
                buildHandler.getInstance().setDifficulty(difficulty);
                buildHandler.getInstance().setLinearity(linear);
                buildHandler.getInstance().setSpinning(spinning);
                buildHandler.getInstance().setShields(shieldNum);
                dispose();

                new GameFrame();
                KUVidGame.getInstance().shooterStart();
                Application.getInstance().startGame(new Thread(KUVidGame.getInstance()));
                
            }
        });
        
        this.Load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

            	KUVidGame.getInstance().setIsLoad(true);
                
                dispose();

                new GameFrame();
                Application.getInstance().startGame(new Thread(KUVidGame.getInstance()));
            }
        });

        SpinButton.addItemListener(new SpinHandler(true));
        StationaryButton.addItemListener(new SpinHandler(false));
        LinearButton.addItemListener(new StructureHandler(true));
        NoNLinearButton.addItemListener(new StructureHandler(false));

    }

    public void createGame() {
        atomNumber = Integer.parseInt((String) AtomNumber.getText());
        reactionBlockerNumber = Integer.parseInt((String) ReactionBlockerNumber.getText());
        powerUpNumber = Integer.parseInt((String) PowerUpNumber.getText());
        moleculeNumber = Integer.parseInt((String) MoleculeNumber.getText());
        L = Integer.parseInt((String) LTextField.getText());
        difficulty = ComboBox.getItemAt(ComboBox.getSelectedIndex());

        buildHandler.getInstance().setNumAtoms(atomNumber);
        buildHandler.getInstance().setNumMolecules(moleculeNumber);
        buildHandler.getInstance().setNumBlocker(reactionBlockerNumber);
        buildHandler.getInstance().setNumPowerUp(powerUpNumber);
        buildHandler.getInstance().setL(L);
        buildHandler.getInstance().setDifficulty(difficulty);
        buildHandler.getInstance().setLinearity(linear);
        buildHandler.getInstance().setSpinning(spinning);

        KUVidGame.getInstance().shooterStart();
        dispose();

        new GameFrame();
        Application.getInstance().startGame(new Thread(KUVidGame.getInstance()));
    }

    // private inner class to handle the movement of the molecules
    class SpinHandler implements ItemListener {
        private final boolean spin;

        public SpinHandler(boolean bool) {
            spin = bool;
        }

        @Override
        public void itemStateChanged(ItemEvent event) {
            spinning = spin;
        }
    }

    // private inner class to handle the structure of the molecules
    class StructureHandler implements ItemListener {
        private final boolean linearity;

        public StructureHandler(boolean bool) {
            linearity = bool;
        }

        @Override
        public void itemStateChanged(ItemEvent event) {
            linear = linearity;
        }
    }
   
}