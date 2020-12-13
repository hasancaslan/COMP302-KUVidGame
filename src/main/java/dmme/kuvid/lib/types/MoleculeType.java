package dmme.kuvid.lib.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MoleculeType{
	ALPHA,
	BETA,
	GAMMA,
	SIGMA;
	
	private static final List<MoleculeType> VALUES =Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static MoleculeType randomMoleculeType()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
}
