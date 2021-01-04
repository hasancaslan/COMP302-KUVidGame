package dmme.kuvid.ui;

import dmme.kuvid.domain.GameObjects.GameObject;


public class Factory {
	
	public static GamePanel panel;
	
	public Factory(GamePanel gamePanel) {
		Factory.panel=gamePanel;
	}
	
	public static void createAlphaUI(GameObject atom) {
		alphaAtomUI alpha=new alphaAtomUI(atom,Factory.panel);	
	}
	
	public static void createGammaUI(GameObject atom) {
		gammaAtomUI gamma=new gammaAtomUI(atom,Factory.panel);
		
	}
	
	public static void createBetaUI(GameObject atom) {
		betaAtomUI beta=new betaAtomUI(atom,Factory.panel);
		
	}
	public static void createSigmaUI(GameObject atom) {
		sigmaAtomUI sigma= new sigmaAtomUI(atom,Factory.panel);
		
	}
	public static void createAlphaMoleculUI(GameObject mol) {
		alphaMoleculeUI gamma=new alphaMoleculeUI(mol,Factory.panel);
		
	}
	public static void createGammaMoleculUI(GameObject mol) {
		gammaMoleculeUI gamma=new gammaMoleculeUI(mol,Factory.panel);
		
	}
	
	public static void createBetaMoleculUI(GameObject mol) {
		betaMoleculeUI beta=new betaMoleculeUI(mol,Factory.panel);
		
	}
	public static void createSigmaMoleculUI(GameObject mol) {
		sigmaMoleculeUI sigma= new sigmaMoleculeUI(mol,Factory.panel);
		
	}
	
	public static void createAlphaBlockerUI(GameObject mol) {
		alphaBlockerUI alpha=new alphaBlockerUI(mol,Factory.panel);
		
	}
	public static void createGammaBlockerUI(GameObject mol) {
		gammaBlockerUI gamma=new gammaBlockerUI(mol,Factory.panel);
		
	}
	
	public static void createBetaBlockerUI(GameObject mol) {
		betaBlockerUI beta=new betaBlockerUI(mol,Factory.panel);
		
	}
	public static void createSigmaBlockerUI(GameObject mol) {
		sigmaBlockerUI sigma= new sigmaBlockerUI(mol,Factory.panel);
		
	}
	
	public static void createAlphaPowerUI(GameObject mol) {
		alphaPowerUI alpha=new alphaPowerUI(mol,Factory.panel);
		
	}
	public static void createGammaPowerUI(GameObject mol) {
		gammaPowerUI gamma=new gammaPowerUI(mol,Factory.panel);
		
	}
	
	public static void createBetaPowerUI(GameObject mol) {
		betaPowerUI beta=new betaPowerUI(mol,Factory.panel);
		
	}
	public static void createSigmaPowerUI(GameObject mol) {
		sigmaPowerUI sigma= new sigmaPowerUI(mol,Factory.panel);
		
	}

}
