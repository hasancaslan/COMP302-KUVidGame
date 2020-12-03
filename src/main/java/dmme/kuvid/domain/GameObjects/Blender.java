package dmme.kuvid.domain.GameObjects;

import dmme.kuvid.lib.types.*;
import dmme.kuvid.domain.Controllers.*;

public class Blender {
	private BlenderAction action;
	private AtomType type;
	private int number;
	private createHandler creator;
	private destroyHandler destroyer;
	
	public Blender(createHandler c, destroyHandler d) {
		this.creator=c;
		this.destroyer=d;
	}
	
	public void useBlender(BlenderAction action,AtomType type,int number) {
		this.action=action;
		this.type=type;
		this.number=number;
		
		if (this.action.equals(BlenderAction.Blend)){
			this.blendAtoms();
		}else if(this.action.equals(BlenderAction.Break)) {
			this.breakAtoms();
		}else{
			System.out.println("should throw error !!check here!!");
		}
		
	}
	
	public void blendAtoms() {
		if (this.type.equals(AtomType.SIGMA)) {
			int toBeCreated=this.number*4;
			
			// check enough atoms left
			
			for(int i=this.number;i>0;i--) {
				// destroy beta here
			}
			
			for(int j=toBeCreated;j>0;j--) {
				// Create alpha here
			}
			
		}else if(this.type.equals(AtomType.GAMMA)) {
			int toBeCreated=this.number*3;
			
			// check enough atoms left
			
			for(int i=this.number;i>0;i--) {
				// destroy beta here
			}
			
			for(int j=toBeCreated;j>0;j--) {
				// Create alpha here
			}
			
		}else if(this.type.equals(AtomType.BETA)) {
			int toBeCreated=this.number*2;
			
			// check enough atoms left
			
			for(int i=this.number;i>0;i--) {
				// destroy beta here
			}
			
			for(int j=toBeCreated;j>0;j--) {
				// Create alpha here
			}
			
		}else {
			// no break maybe throw error?
		}
		
	}
	
	public void breakAtoms() {
		
	}

}
