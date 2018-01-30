package fr.unice.polytech.qgl.tests.unit.assertions;

public class Assertion {


    public static void fail(String message) throws AssertionFailedError {
        throw new AssertionFailedError(message);
    }

    public static void assertTrue(boolean condition) throws AssertionFailedError {
        if(!condition)
            fail("Condition is not true");
    }

    public static void assertFalse(boolean condition) throws AssertionFailedError {
        if(condition)
            fail("Condition is not false");
    }

    public static void assertEquals(Object oracle, Object actual)  throws AssertionFailedError {
        if(oracle != null) {
            if(!oracle.equals(actual))
                throw new AssertionFailedError("Expected ["+oracle+"], given ["+actual+"]");
        } else if (actual != null) {
                throw new AssertionFailedError("Expected null, given ["+actual+"]");
        }
    }

}
