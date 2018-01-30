package fr.unice.polytech.qgl.tests.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Shop {

    private Catalogue contents;
    private Map<String, Customer> customers;
    private Bank bank;

    public Shop(Catalogue remote) { this(remote, new MyBank());  }

    public Shop(Catalogue remote, Bank bank) {
        this.contents = remote;
        this.customers = new HashMap<>();
        this.bank = bank;
    }

    public void register(String name, String cardNumber) {
        this.customers.put(name, new Customer(name, cardNumber));
    }

    public void purchase(String barCode, String customerName, int quantity) {
        Product toPurchase = retrieveAProduct(barCode);
        Customer cust = retrieveACustomer(customerName);
        cust.addToCart(toPurchase, quantity);
    }

    public void pay(String customerName) throws Bank.PaymentException {
        Customer cust = retrieveACustomer(customerName);
        double price = cust.prepareForPayment();
        bank.pay(cust.getCardNumber(), price);
    }

    public Product retrieveAProduct(String barCode) {
        Optional<Product> toPurchase = contents.findByBarCode(barCode);
        if(! toPurchase.isPresent())
            throw new RuntimeException("Unknown product ["+barCode+"]");
        return toPurchase.get();
    }

    private Customer retrieveACustomer(String customerName) {
        Customer c = customers.get(customerName);
        if (c == null)
            throw new RuntimeException("Unknown Customer [" + customerName + "]");
        return c;
    }

}
