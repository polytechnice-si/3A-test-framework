package tests.calculator;

public class MyPrettyCalculator implements Multiplier, Divider {

    @Override public int multiply(int a, int b) {
        return a * b;
    }

    @Override public int divide(int a, int b) {
        return a / b;
    }
}