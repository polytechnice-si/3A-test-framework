package demo.unit;

import fr.unice.polytech.qgl.tests.shop.Catalogue;
import fr.unice.polytech.qgl.tests.shop.Customer;
import fr.unice.polytech.qgl.tests.shop.Product;
import fr.unice.polytech.qgl.tests.shop.Shop;
import fr.unice.polytech.qgl.tests.unit.annotations.*;

import java.rmi.server.ExportException;
import java.util.Optional;

import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class ShopTest {

    private static final Product aProduct = new Product("aProduct", 42.0);
    private static final Product anotherProduct = new Product("anotherProduct", 1.5);

    private Shop theShop;
    @Before public void initialize() { theShop = new Shop(new TestCatalogue()); }
    @After public void cleanup()     { this.theShop = null; }


    @Test public void supportCustomerRegistrationAndPurchase() {
        theShop.register("bob", "1234567890");
        theShop.purchase("666", "bob", 2);
    }

    @Test public void supportProductFinding() {
        assertEquals(aProduct, theShop.retrieveAProduct("42"));
    }

    @Test(expected = RuntimeException.class)
    public void rejectUnknownCustomer() {
        theShop.purchase("41","bob", 2);
    }


    @Test(expected = RuntimeException.class)
    public void rejectUnknownProductPurchase() {
        theShop.register("bob", "1234567890");
        theShop.purchase("66642", "bob", 2);
    }

    @Test(expected = RuntimeException.class)
    public void rejectUnknownProduct() {
        theShop.retrieveAProduct("UNKOWN");
    }

    private class TestCatalogue implements Catalogue {

        @Override public Optional<Product> findByBarCode(String barCode) {
            switch (barCode) {
                case "42":
                    return Optional.of(aProduct);
                case "666":
                    return Optional.of(anotherProduct);
            }
            return Optional.empty();
        }

        @Override public Optional<Product> findByProductName(String productName) {
            switch (productName) {
                case "aProduct":
                    return Optional.of(aProduct);
                case "anotherProduct":
                    return Optional.of(anotherProduct);
            }
            return Optional.empty();
        }
    }

}


