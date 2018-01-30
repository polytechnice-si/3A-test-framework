package fr.unice.polytech.qgl.tests.shop;

import java.util.Optional;

public interface Catalogue {

    Optional<Product> findByBarCode(String barCode);
    Optional<Product> findByProductName(String productName);

}
