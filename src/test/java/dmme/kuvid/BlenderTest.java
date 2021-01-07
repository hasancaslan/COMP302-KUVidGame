package dmme.kuvid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import dmme.kuvid.domain.Controllers.*;
import dmme.kuvid.domain.GameObjects.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.*;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.ui.*;

class BlenderTest {
	GameObject atom;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		new GameFrame();
	}
	
	@Test
	void testAllEmpty() {
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.ALPHA);
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.BETA);
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.GAMMA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.ALPHA);
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.BETA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.BETA, AtomType.ALPHA);
		
		
		assertEquals(numAlpha,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numBeta,KUVidGame.getInstance().getNumAtom(AtomType.BETA));
		assertEquals(numGamma,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numSigma,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));

	}
	
	@Test
	void testBlendALPHAToSIGMA() {
		
		DomainFactory.createAtom(AtomType.ALPHA, 10);
		
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.ALPHA);
		
		assertEquals(numAlpha-4,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numSigma+1,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));


	}
	
	@Test
	void testBlendBETAToSIGMA() {
		DomainFactory.createAtom(AtomType.BETA, 10);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.BETA);
		
		assertEquals(numBeta-3,KUVidGame.getInstance().getNumAtom(AtomType.BETA));
		assertEquals(numSigma+1,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));


	}
	
	@Test
	void testBlendGAMMAToSIGMA() {
		DomainFactory.createAtom(AtomType.GAMMA, 10);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.GAMMA);
		
		assertEquals(numGamma-2,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numSigma+1,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));


	}
	
	@Test
	void testBlendALPHAToGAMMA() {
		DomainFactory.createAtom(AtomType.ALPHA, 10);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.ALPHA);
		
		assertEquals(numGamma+1,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numAlpha-3,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));


	}
	
	@Test
	void testBlendBETAToGAMMA() {
		DomainFactory.createAtom(AtomType.BETA, 10);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.BETA);
		
		assertEquals(numGamma+1,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numBeta-2,KUVidGame.getInstance().getNumAtom(AtomType.BETA));


	}
	
	@Test
	void testBlendALPHAToBETA() {
		
		DomainFactory.createAtom(AtomType.ALPHA, 10);
		
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		
		assertEquals(numAlpha,10);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.BETA, AtomType.ALPHA);
		
		assertEquals(numAlpha-2,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numBeta+1,KUVidGame.getInstance().getNumAtom(AtomType.BETA));


	}

}
