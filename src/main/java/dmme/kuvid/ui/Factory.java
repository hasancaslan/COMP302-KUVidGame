package dmme.kuvid.ui;

import dmme.kuvid.domain.GameObjects.GameObject;


public class Factory {
	
	public static GamePanel panel;
	
	public Factory(GamePanel gamePanel) {
		Factory.panel=gamePanel;
	}
	
	public static void createAlphaUI(GameObject atom) {
		alphaAtomUI alpha=new alphaAtomUI(atom);	
	}
	
	public static void createGammaUI(GameObject atom) {
		gammaAtomUI gamma=new gammaAtomUI(atom);
		
	}
	
	public static void createBetaUI(GameObject atom) {
		betaAtomUI beta=new betaAtomUI(atom);
		
	}
	public static void createSigmaUI(GameObject atom) {
		sigmaAtomUI sigma= new sigmaAtomUI(atom);
		
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

}
