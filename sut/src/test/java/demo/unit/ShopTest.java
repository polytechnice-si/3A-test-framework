package demo.unit;

import static fr.unice.polytech.qgl.tests.peano.PeanoInteger.ZERO;
import fr.unice.polytech.qgl.tests.unit.annotations.*;
import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class PeanoIntegerTests {

    @Test
    public void zeroIsZero() { assertTrue("ZERO must be equals to ZERO", ZERO.isZero()); }

    @Test
    public void incrementLEadsToGreaterInteger() { assertTrue("i.S() > i", ZERO.S().gt(ZERO)); }

    @Test
    public void incrementAndThenDecrement() { assertTrue("i.S() > i", ZERO.S().D().isZero()); }

}


