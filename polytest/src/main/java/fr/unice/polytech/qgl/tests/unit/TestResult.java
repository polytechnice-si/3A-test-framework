package fr.unice.polytech.qgl.tests.unit;

import java.lang.reflect.Method;

public class TestResult {

    private Method test;
    private boolean success;
    private String message;

    TestResult(Method test, boolean success, String message) {
        this.test = test;
        this.success = success;
        this.message = message;
    }

    TestResult(Method test, boolean success) { this(test, success, "");  }

    @Override
    public String toString() {
        return test.getName() + ": " + (success? "OK": "FAILURE") + (message.equals("") ? "" : " ["+message+"]");
    }
}
