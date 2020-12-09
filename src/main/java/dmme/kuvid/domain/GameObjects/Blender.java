package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.lib.types.*;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.*;

public class Blender {
	private BlenderAction action;
	private createHandler creator;
	private destroyHandler destroyer;
	
	public Blender(createHandler c, destroyHandler d) {
		this.creator=c;
		this.destroyer=d;
	}
	
	public void useBlender(BlenderAction action,AtomType typeCreate,AtomType typeDestroy) {
		this.action=action;
		
		if (this.action.equals(BlenderAction.Blend)){
			this.blendAtoms(typeCreate,typeDestroy);
		}else if(this.action.equals(BlenderAction.Break)) {
			this.breakAtoms(typeCreate,typeDestroy);
		}else{
			System.out.println("should throw error !!check here!!");
		}
		
	}
	
	private void breakAtoms(AtomType typeCreate,AtomType typeDestroy) {
		if (typeDestroy.equals(AtomType.SIGMA)&KUVidGame.getInstance().getNumAtom(AtomType.SIGMA)>=1) {
		
			if(typeCreate.equals(AtomType.ALPHA)) {
				
				//destroyBlender(AtomType.SIGMA)
				
				for(int i=4;i>0;i--) {					
					// createAtom(AtomType.ALPHA)
				}
				
				
			}else if(typeCreate.equals(AtomType.BETA)) {
				
				// destroyBlender(AtomType.SIGMA)
				
				for(int i=3;i>0;i--) {					
					// createAtom(AtomType.BETA)
				}
				
			}else if(typeCreate.equals(AtomType.GAMMA)) {
				
				// destroyBlender(AtomType.SIGMA)
				
				for(int i=2;i>0;i--) {					
					// createAtom(AtomType.GAMMA)
				}
			}
			
		}else if (typeDestroy.equals(AtomType.GAMMA)&KUVidGame.getInstance().getNumAtom(AtomType.GAMMA)>=1) {
		
			if(typeCreate.equals(AtomType.ALPHA)) {
				
				//destroyBlender(AtomType.GAMMA)
				
				for(int i=3;i>0;i--) {					
					// createAtom(AtomType.ALPHA)
				}
				
				
			}else if(typeCreate.equals(AtomType.BETA)) {
				
				// destroyBlender(AtomType.GAMMA)
				
				for(int i=2;i>0;i--) {					
					// createAtom(AtomType.BETA)
				}
			}
			
		}else if(typeDestroy.equals(AtomType.BETA)&KUVidGame.getInstance().getNumAtom(AtomType.BETA)>=1) {
				
			if(typeCreate.equals(AtomType.ALPHA)) {
				
				//destroyBlender(AtomType.BETA)
				
				for(int i=2;i>0;i--) {					
					// createAtom(AtomType.ALPHA)
				}
				
				
			}			
		}else {
			// no break maybe throw error?
		}
		
	}
	
	private void blendAtoms(AtomType typeCreate,AtomType typeDestroy) {
			if (typeCreate.equals(AtomType.SIGMA)) {
			
			// check enough atoms left
				if(typeDestroy.equals(AtomType.ALPHA) & 
						(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA)>=4)) {
					
					for(int i=4;i>0;i--) {
						// destroy beta here destroyBlender(AtomType)
					}
				
				// createAtom(AtomType)
			}else if(typeDestroy.equals(AtomType.BETA) & 
					(KUVidGame.getInstance().getNumAtom(AtomType.BETA)>=3)) {
				
				for(int i=3;i>0;i--) {
					// destroy beta here destroyBlender(AtomType.BETA)
				}
				
				// createAtom(AtomType.BETA)
			}else if(typeDestroy.equals(AtomType.GAMMA) & 
					(KUVidGame.getInstance().getNumAtom(AtomType.GAMMA)>=2)) {
				
				for(int i=2;i>0;i--) {
					// destroy beta here destroyBlender(AtomType.GAMMA)
				}
				
				// createAtom(AtomType.GAMMA)
			}
			
		}else if(typeCreate.equals(AtomType.GAMMA)) {
			
			if(typeDestroy.equals(AtomType.ALPHA) & 
					(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA)>=3)) {
				
				for(int i=3;i>0;i--) {
					// destroy beta here destroyBlender(AtomType)
				}
				
				// createAtom(AtomType)
			}else if(typeDestroy.equals(AtomType.BETA) & 
					(KUVidGame.getInstance().getNumAtom(AtomType.BETA)>=2)) {
				
				for(int i=2;i>0;i--) {
					// destroy beta here destroyBlender(AtomType.BETA)
				}
				
				// createAtom(AtomType.BETA)
			}
			
		}else if(typeCreate.equals(AtomType.BETA)) {
			if(typeDestroy.equals(AtomType.ALPHA) & 
					(KUVidGame.getInstance().getNumAtom(AtomType.ALPHA)>=2)) {
				
				for(int i=2;i>0;i--) {
					// destroy beta here destroyBlender(AtomType)
				}
				
				// createAtom(AtomType)
			}
			
		}else {
			// no break maybe throw error?
		}
	}

}
