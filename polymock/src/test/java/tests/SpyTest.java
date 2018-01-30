package tests;

import fr.unice.polytech.qgl.tests.unit.annotations.*;
import tests.calculator.*;

import static fr.unice.polytech.qgl.tests.mock.Spy.howManyTimesWasCalled;
import static fr.unice.polytech.qgl.tests.mock.Spy.spy;
import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class SpyTest {

    private Multiplier aMultiplier;
    private Multiplier aSpy;

    @Before public void setUp() {
        aMultiplier = new MyPrettyMultiplier();
        aSpy = spy(Multiplier.class, MyPrettyMultiplier.class);
    }

    @After public void tearDown() {
        aMultiplier = null;
        aSpy = null;
    }

    @Test public void aSpyCanBeCreatedBasedOnItsInterface() {
        Divider aDivider = spy(Divider.class);
        assertEquals(2, aDivider.divide(6, 3));
    }

    @Test(expected = RuntimeException.class)
    public void aSpyCannotBeCreatedWhenAmbiguous() {
        Multiplier ambiguous = spy(Multiplier.class);
    }

    @Test public void aSpyDoesNotInterfereWithTheObject() {
        assertEquals(aMultiplier.multiply(2,3), aSpy.multiply(2,3));
    }

    @Test public void aSpyCountsHowManyTimeAMethodIsInvoked() {
        assertEquals(aMultiplier.multiply(2,3), aSpy.multiply(2,3));
        assertEquals(1, howManyTimesWasCalled(aSpy, "multiply"));
        assertEquals(aMultiplier.multiply(4,5), aSpy.multiply(4,5));
        assertEquals(2, howManyTimesWasCalled(aSpy, "multiply"));
    }

    @Test public void aSpyCountsAtTheObjectLevel() {
        Multiplier anotherSpy = spy(Multiplier.class, MyPrettyCalculator.class);
        assertEquals(6,  aSpy.multiply(2,3));
        assertEquals(20, aSpy.multiply(4,5));
        assertEquals(2, howManyTimesWasCalled(aSpy, "multiply"));
        assertEquals(0, howManyTimesWasCalled(anotherSpy, "multiply"));
        assertEquals(0, howManyTimesWasCalled(anotherSpy, "divide"));

        assertEquals(12,  anotherSpy.multiply(4,3));
        assertEquals(2, howManyTimesWasCalled(aSpy, "multiply"));
        assertEquals(1, howManyTimesWasCalled(anotherSpy, "multiply"));
        assertEquals(0, howManyTimesWasCalled(anotherSpy, "divide"));
    }

}
