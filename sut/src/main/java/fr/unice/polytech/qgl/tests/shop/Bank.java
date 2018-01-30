package fr.unice.polytech.qgl.tests.shop;

public interface Bank {

    class PaymentException extends Exception { }

    void pay(String cardNumber, double amount) throws PaymentException;

}
