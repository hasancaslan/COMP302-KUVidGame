package dmme.kuvid.lib.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PowerType {
    SIGMA_B,
    BETA_B,
    ALPHA_B,
    GAMMA_B;
	
	
	private static final List<PowerType> VALUES =Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static PowerType randomPowerType()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }

}
