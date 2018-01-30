package fr.unice.polytech.qgl.tests.shop;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> contents;

    public Cart() {
        this.contents = new HashMap<>();
    }

    public void add(Product p, int amount) {
        Integer existing = contents.getOrDefault(p,0);
        contents.put(p, existing + amount);
    }

    public void remove(Product p, int amount) {
        Integer existing = contents.getOrDefault(p,0);
        if(existing < amount)
            throw new IllegalArgumentException("Bad quantity: " + amount);
        contents.put(p, existing - amount);
    }

    public double flush() {
        double value = contents.keySet()
                .stream()
                .map( product -> product.getPrice() * contents.get(product) )
                .reduce((a,b) -> a + b).orElse(0.0);
        contents = new HashMap<>();
        return value;
    }

}
