package dmme.kuvid.ui;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.utils.observer.PropertyEvent;
import dmme.kuvid.utils.observer.PropertyListener;


public class Factory implements PropertyListener{
	
	public static GamePanel panel;
	
	public Factory(GamePanel gamePanel) {
		Factory.panel=gamePanel; 
		DomainFactory.getInstance().addPropertyListener("alpha",this);
		DomainFactory.getInstance().addPropertyListener("beta",this);
		DomainFactory.getInstance().addPropertyListener("gamma",this);
		DomainFactory.getInstance().addPropertyListener("sigma",this);
		DomainFactory.getInstance().addPropertyListener("alphaMol",this);
		DomainFactory.getInstance().addPropertyListener("betaMol",this);
		DomainFactory.getInstance().addPropertyListener("gammaMol",this);
		DomainFactory.getInstance().addPropertyListener("sigmaMol",this);
		DomainFactory.getInstance().addPropertyListener("alphaPower",this);
		DomainFactory.getInstance().addPropertyListener("betaPower",this);
		DomainFactory.getInstance().addPropertyListener("gammaPower",this);
		DomainFactory.getInstance().addPropertyListener("sigmaPower",this);
		DomainFactory.getInstance().addPropertyListener("alphaBlocker",this);
		DomainFactory.getInstance().addPropertyListener("betaBlocker",this);
		DomainFactory.getInstance().addPropertyListener("gammaBlocker",this);
		DomainFactory.getInstance().addPropertyListener("sigmaBlocker",this);
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

	@Override
	public void onPropertyEvent(PropertyEvent e) {
		// TODO Auto-generated method stub
		if(e.getPropertyName().equals("alpha")) {
			
			Factory.createAlphaUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("beta")) {
			
			Factory.createBetaUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("gamma")) {
			
			Factory.createGammaUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("sigma")) {
			
			Factory.createSigmaUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("alphaMol")) {
			
			Factory.createAlphaMoleculUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("betaMol")) {
			
			Factory.createBetaMoleculUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("gammaMol")) {
			
			Factory.createGammaMoleculUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("sigmaMol")) {
			
			Factory.createSigmaMoleculUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("alphaPower")) {
			
			Factory.createAlphaPowerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("betaPower")) {
			
			Factory.createBetaPowerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("gammaPower")) {
			
			Factory.createGammaPowerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("sigmaPower")) {
			
			Factory.createSigmaPowerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("alphaBlocker")) {
			
			Factory.createAlphaBlockerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("betaBlocker")) {
			
			Factory.createBetaBlockerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("gammaBlocker")) {
			
			Factory.createGammaBlockerUI((GameObject)e.getNewValue());
			
		}else if(e.getPropertyName().equals("sigmaBlocker")) {
			
			Factory.createSigmaBlockerUI((GameObject)e.getNewValue());
			
		}
		
	}

}
