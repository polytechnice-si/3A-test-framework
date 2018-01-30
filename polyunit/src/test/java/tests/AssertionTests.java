package tests;

import fr.unice.polytech.qgl.tests.unit.annotations.*;
import fr.unice.polytech.qgl.tests.unit.assertions.AssertionFailedError;

import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class AssertionTests {

    private int threshold = 0;

    @Before public void setUp() { threshold++; }

    @After public void tearDown() { threshold--; }

    @Test(expected = AssertionFailedError.class)
    public void failShouldFail() { fail("This one should fail"); }

    @Test public void failOnPurpose() { fail("This one should fail"); }

    @Test
    @Ignore
    public void failOnPurposeButIgnored() { fail("This one should fail"); }

    @Test public void assertTrueWorks() {
        assertTrue(threshold > 0);
    }

    @Test(expected = AssertionFailedError.class)
    public void assertTrueFailsWhenRelevant() {
        assertTrue( threshold <= 0);
    }

    @Test public void assertFalseWorks() {
        assertFalse(threshold <= 0);
    }

    @Test(expected = AssertionFailedError.class)
    public void assertFalseFailsWhenRelevant() {
        assertFalse( threshold > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void identifyThrownException() {
        throw new IllegalArgumentException("bad");
    }

}