package fr.unice.polytech.qgl.tests.shop;

import java.util.Objects;

public class Customer {

    private String name;
    private String cardNumber;
    private Cart collectedProducts;

    public String getCardNumber() { return cardNumber; }

    public Customer(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.collectedProducts = new Cart();
    }

    public void addToCart(Product p, int quantity) {
        if(quantity > 0)
            collectedProducts.add(p,quantity);
        else
            collectedProducts.remove(p, -quantity);
    }

    public double prepareForPayment() {
        return collectedProducts.flush();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(cardNumber, customer.cardNumber);
    }

    @Override
    public int hashCode() { return Objects.hash(name, cardNumber); }
}
