package demo.unit;

import fr.unice.polytech.qgl.tests.shop.Cart;
import fr.unice.polytech.qgl.tests.shop.Product;
import fr.unice.polytech.qgl.tests.unit.annotations.*;
import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class CartTest {

    private Cart theCart;
    @Before public void setUp()   { this.theCart = new Cart(); }
    @After  public void cleanup() { this.theCart = null; }

    @Test public void emptyCartCostsZero() { assertEquals(0.0, theCart.flush()); }

    @Test(expected = RuntimeException.class)
    public void rejectExcessiveRemovingOfExistingProduct(){
        Product p = new Product("aProduct", 3.0);
        theCart.add(p, 3);
        theCart.remove(p, 4);
    }

    @Test(expected = RuntimeException.class)
    public void rejectRemovingOfNonSelectedProduct(){
        Product p = new Product("aProduct", 3.0);
        theCart.remove(p, 2);
    }

    @Test public void flushComputeTheRightPrice() {
        Product p = new Product("aProduct", 1.5);
        theCart.add(p, 3);
        Product pPrime = new Product("anotherProduct", 2.5);
        theCart.add(pPrime, 2);
        assertEquals(9.5, theCart.flush());
    }

    @Test public void addingAndRemovingProducts() {
        Product p = new Product("aProduct", 3.0);
        theCart.add(p, 3);
        theCart.remove(p, 1);
        assertEquals(6.0, theCart.flush());
        theCart.add(p, 3);
        theCart.remove(p, 3);
        assertEquals(0.0, theCart.flush());
    }

}


