package dmme.kuvid.ui;

import dmme.kuvid.domain.GameObjects.GameObject;


public class Factory {
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
	public static void createMoleculUI() {
		
	}
}
