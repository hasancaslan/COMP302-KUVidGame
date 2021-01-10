package dmme.kuvid.lib.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ReactionType {
	ALPHA_R,
	BETA_R,
	SIGMA_R,
	GAMMA_R;
	
	
	private static final List<ReactionType> VALUES =Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static ReactionType randomReactionType()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
}
