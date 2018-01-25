package fr.unice.polytech.qgl.tests.unit.assertions;

public class Assertion {


    public static void fail(String message) throws AssertionFailedError {
        throw new AssertionFailedError(message);
    }

    public static void assertTrue(String message, boolean condition) throws AssertionFailedError {
        if(!condition)
            fail(message);
    }

    public static void assertFalse(String message, boolean condition) throws AssertionFailedError {
        if(condition)
            fail(message);
    }

    public static void assertEquals(String message, Object oracle, Object actual)  throws AssertionFailedError {
        if(oracle != null) {
            if(!oracle.equals(actual))
                throw new AssertionFailedError(message);
        } else if (actual != null) {
                throw new AssertionFailedError(message);
        }
    }


}
