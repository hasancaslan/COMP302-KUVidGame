package dmme.kuvid.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

class BuildingWindow extends JFrame {
    
    String[] Difficulty = new String[]{"Easy", "Medium", "Hard"};
    String difficulty;
    
    JTextField AtomNumber = new JTextField(8);
    JTextField ReactionBlockerNumber = new JTextField(8);
    JTextField PowerUpNumber = new JTextField(8);
    JTextField MoleculeNumber = new JTextField(8);
    JTextField GameSize = new JTextField(8);
    
    ButtonGroup spinGroup;
    ButtonGroup movementGroup;
    
    JRadioButton SpinButton; 
    JRadioButton StationaryButton; 
    JRadioButton LinearButton; 
    JRadioButton NoNLinearButton; 
    
    JComboBox ComboBox;
    JButton StartButton;

    int atomNumber = 0;
	int reactionBlockerNumber = 0;
	int powerUpNumber = 0;
	int moleculeNumber = 0;
	int gameSize = 0;

	public boolean Spinning = false;	//for Alpha and Beta
	public boolean linear = true;
	
    public BuildingWindow() {
        this.ComboBox = new JComboBox(this.Difficulty);
        this.StartButton = new JButton("Start Game");      
        this.setLayout(new GridLayout(9, 2, 4, 4));
        
        this.add(new JLabel("Number of Atoms: "));
        this.add(this.AtomNumber);
        this.add(new JLabel("Number of ReactionBlockers: "));
        this.add(this.ReactionBlockerNumber);
        this.add(new JLabel("Number of PowerUps: "));
        this.add(this.PowerUpNumber);
        this.add(new JLabel("Number of Molecules: "));
        this.add(this.MoleculeNumber);
        
        SpinButton =new JRadioButton("Spinning Molecules", false);
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
       
        this.add(new JLabel("Game size in terms of L: "));
        this.add(this.GameSize);
               
        this.add(new JLabel("GameDifficulty"));
        this.add(this.ComboBox);

        this.add(this.StartButton);        
        
        this.StartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
                atomNumber =  Integer.parseInt((String)AtomNumber.getText());
            	reactionBlockerNumber = Integer.parseInt((String)ReactionBlockerNumber.getText());
            	powerUpNumber = Integer.parseInt((String)PowerUpNumber.getText());
            	moleculeNumber  = Integer.parseInt((String)MoleculeNumber.getText());
            	gameSize = Integer.parseInt((String)GameSize.getText());		
            	difficulty = (String) ComboBox.getItemAt(ComboBox.getSelectedIndex());
              		            	
            	BuildingWindow.this.getContentPane().removeAll();
            	BuildingWindow.this.getContentPane().repaint();
            }
        });
        SpinButton.addItemListener(new SpinHandler(true));
        StationaryButton.addItemListener(new SpinHandler(false));
        LinearButton.addItemListener(new StructureHandler(true));
        NoNLinearButton.addItemListener(new StructureHandler(false));
     } 



     // private inner class to handle the movement of the molecules
     class SpinHandler implements ItemListener 
     {
        private boolean spin;
        public SpinHandler(boolean bool){ spin = bool; }

		@Override
		public void itemStateChanged(ItemEvent event) {
			Spinning = spin;
		} 
     } 
     
     // private inner class to handle the structure of the molecules
     class StructureHandler implements ItemListener 
     {
    	 private boolean linearity;

         public StructureHandler(boolean bool) { linearity = bool; }

		@Override
		public void itemStateChanged(ItemEvent event) {
			linear = linearity;
		} 
     }     
}