package dmme.kuvid.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dmme.kuvid.constants.Config;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.AtomType;
import dmme.kuvid.lib.types.BlenderAction;
import dmme.kuvid.lib.types.Key;
import dmme.kuvid.lib.types.ObjectType;
import dmme.kuvid.utils.IconImporter;

public class BlenderWindow extends JFrame {
	
	 private final String DEFAULT_SOURCE_AMOUNT = "1";
	 private final String DEFAULT_RESULT_AMOUNT = "2";
	    
	    
	 JTextField SourceAtom = new JTextField(DEFAULT_SOURCE_AMOUNT, 8);
	 JTextField ResultAtom = new JTextField(DEFAULT_RESULT_AMOUNT, 8);
	 ButtonGroup actionGroup;
	 JRadioButton BreakButton;
	 JRadioButton BlendButton;
	 JButton DoneButton;
	 
	 public BlenderWindow() {
		 this("UBK");
	 }
	

	    public BlenderWindow(String test) {
	        this.setTitle("BLENDER WINDOW");
	        this.setSize(510, 510);
	        this.setLocationRelativeTo((Component) null);
	        this.setDefaultCloseOperation(3);
	        this.setVisible(true);

	       this.setLayout(new GridLayout(7,1));
	        
	        this.DoneButton=new JButton("DONE");
	        
	        this.add(new JLabel("Source Atom Rank: "));
	        this.add(this.SourceAtom);
	        this.add(new JLabel("Result Atom Rank: "));
	        this.add(this.ResultAtom);

	        BreakButton = new JRadioButton("BREAK");
	        BlendButton = new JRadioButton("BLEND");


	        this.add(this.BreakButton);
	        this.add(this.BlendButton);
	        
	        this.BlendButton.setSelected(true);

	        actionGroup = new ButtonGroup();
	        actionGroup.add(BreakButton);
	        actionGroup.add(BlendButton);
	        
	        this.add(this.DoneButton);
	        
	        this.DoneButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	            	doWork();
	            	
	            }
	          });
	        
	        this.getRootPane().setDefaultButton(DoneButton);
	        
	    }
	    
	    public void doWork() {
	    	int source=Integer.parseInt((String) SourceAtom.getText());
	    	int result=Integer.parseInt((String) ResultAtom.getText());
	    	
	    	AtomType sourceType=AtomType.values()[source-1];
	    	AtomType resultType=AtomType.values()[result-1];
	    	
	    	BlenderAction action=null;
	    	
	    	if(BreakButton.isSelected()) {
	    		action=BlenderAction.Break;
	    	}else if(BlendButton.isSelected()) {
	    		action=BlenderAction.Blend;
	    	}
	    	
	    /*	System.out.println("Source: "+sourceType+" Result: "+resultType);
	    	System.out.println("Before Blender:");
	    	System.out.println(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,sourceType)).size());
	    	System.out.println(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,resultType)).size());
	    */
	    	
	    	KUVidGame.getInstance().useBlender(action, resultType, sourceType);
	    	dispose();
	    	KUVidGame.getInstance().resumeGame();
	    	
	    /*
	    	System.out.println("After Blender:");
	    	System.out.println(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,sourceType)).size());
	    	System.out.println(KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM,resultType)).size());
	    */
	    }




}
