package fr.unice.polytech.qgl.tests.shop;

public class Bank {

    class PaymentException extends Exception { }

    public void pay(String cardNumber, double amount) throws PaymentException {
        System.out.println("Payment: " + cardNumber + " / " + amount);
    }

}
