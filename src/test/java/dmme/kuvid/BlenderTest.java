package dmme.kuvid;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dmme.kuvid.domain.Controllers.*;
import dmme.kuvid.domain.*;
import dmme.kuvid.lib.types.*;


class BlenderTest {
	
	@BeforeEach
	void setUp() throws Exception {
		KUVidGame.getInstance();
		 KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.ALPHA)).clear();
	     KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.BETA)).clear();
	     KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.GAMMA)).clear();
	     KUVidGame.getGameObjectMap().get(new Key(ObjectType.ATOM, AtomType.SIGMA)).clear();
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
	void testBlendALPHAtoSIGMA() {
		
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 10);
		
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.ALPHA);
		
		assertEquals(numAlpha-4,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numSigma+1,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));


	}
	
	@Test
	void testBlendBETAtoSIGMA() {
		DomainFactory.getInstance().createAtom(AtomType.BETA, 10);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.BETA);
		
		assertEquals(numBeta-3,KUVidGame.getInstance().getNumAtom(AtomType.BETA));
		assertEquals(numSigma+1,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));


	}
	
	@Test
	void testBlendGAMMAtoSIGMA() {
		DomainFactory.getInstance().createAtom(AtomType.GAMMA, 10);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.GAMMA);
		
		assertEquals(numGamma-2,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numSigma+1,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));


	}
	
	@Test
	void testBlendALPHAtoGAMMA() {
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 10);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.ALPHA);
		
		assertEquals(numGamma+1,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numAlpha-3,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));


	}
	
	@Test
	void testBlendBETAtoGAMMA() {
		DomainFactory.getInstance().createAtom(AtomType.BETA, 10);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.BETA);
		
		assertEquals(numGamma+1,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numBeta-2,KUVidGame.getInstance().getNumAtom(AtomType.BETA));


	}
	
	@Test
	void testBlendALPHAtoBETA() {
		
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 10);
		
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		
		assertEquals(numAlpha,10);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.BETA, AtomType.ALPHA);
		
		assertEquals(numAlpha-2,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numBeta+1,KUVidGame.getInstance().getNumAtom(AtomType.BETA));


	}
	
	@Test
	void testCreateForLoop() {
		DomainFactory.getInstance().createAtom(AtomType.ALPHA, 1);
		
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		
		assertEquals(numAlpha,1);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.BETA, AtomType.ALPHA);
		
		assertEquals(numAlpha,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numBeta,KUVidGame.getInstance().getNumAtom(AtomType.BETA));
	}
	
	@Test
	void testNoMatchingType() {
		
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		
		assertEquals(numAlpha,0);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.ALPHA, AtomType.BETA);
		
		assertEquals(numAlpha,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numBeta,KUVidGame.getInstance().getNumAtom(AtomType.BETA));
	}
	
	@Test
	void testSameType() {
		int numAlpha=KUVidGame.getInstance().getNumAtom(AtomType.ALPHA);
		int numBeta=KUVidGame.getInstance().getNumAtom(AtomType.BETA);
		int numGamma=KUVidGame.getInstance().getNumAtom(AtomType.GAMMA);
		int numSigma=KUVidGame.getInstance().getNumAtom(AtomType.SIGMA);
		
		assertEquals(numAlpha,0);
		assertEquals(numBeta,0);
		assertEquals(numGamma,0);
		assertEquals(numSigma,0);
		
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.SIGMA, AtomType.SIGMA);
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.BETA, AtomType.BETA);
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.GAMMA, AtomType.GAMMA);
		KUVidGame.getInstance().useBlender(BlenderAction.Blend, AtomType.ALPHA, AtomType.ALPHA);
		
		
		assertEquals(numAlpha,KUVidGame.getInstance().getNumAtom(AtomType.ALPHA));
		assertEquals(numBeta,KUVidGame.getInstance().getNumAtom(AtomType.BETA));
		assertEquals(numGamma,KUVidGame.getInstance().getNumAtom(AtomType.GAMMA));
		assertEquals(numSigma,KUVidGame.getInstance().getNumAtom(AtomType.SIGMA));

	}

}
