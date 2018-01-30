package fr.unice.polytech.qgl.tests.shop;

public class MyBank implements Bank {

    public void pay(String cardNumber, double amount) throws PaymentException {
        System.out.println("\n*****\n** Payment: " + cardNumber + " / " + amount + "\n*****\n");
    }

}
