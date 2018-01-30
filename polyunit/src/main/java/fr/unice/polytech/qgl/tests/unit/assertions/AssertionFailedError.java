package fr.unice.polytech.qgl.tests.unit.assertions;

public class AssertionFailedError extends Error {

    AssertionFailedError(String message) { super(message); }

    @Override public String toString() { return getMessage(); }

}
