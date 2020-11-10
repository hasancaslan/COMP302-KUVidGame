package dmme.kuvid;

import dmme.kuvid.utils.RepresentationInvariant;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class TestBase {

    protected void testReport(RepresentationInvariant invariant) {
        System.out.println("Testing representation invariant: " + invariant);
        assertTrue(invariant.repSuccess());
    }
}