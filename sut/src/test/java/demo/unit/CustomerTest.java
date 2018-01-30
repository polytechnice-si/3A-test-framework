package demo.unit;

import fr.unice.polytech.qgl.tests.shop.Customer;
import fr.unice.polytech.qgl.tests.shop.Product;
import fr.unice.polytech.qgl.tests.unit.annotations.*;

import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class CustomerTest {

    private Customer theCustomer;
    @Before public void setUp()   { this.theCustomer = new Customer("bob", "987654321"); }
    @After  public void cleanup() { this.theCustomer = null; }

    @Test public void creditCardNumberIsAvailable() {
        assertEquals("987654321", theCustomer.getCardNumber());
    }

    @Test public void supportProductAddingAndRemoval() {
        Product aProduct = new Product("aProduct", 1.5);
        Product anotherProduct = new Product("anotherProduct", 4.5);
        theCustomer.addToCart(aProduct, 2);
        theCustomer.addToCart(anotherProduct, 3);
        theCustomer.addToCart(aProduct, -1);
        theCustomer.addToCart(anotherProduct, -2);
        assertEquals(6.0, theCustomer.prepareForPayment());
    }

    @Test(expected = RuntimeException.class)
    public void rejectExcessiveRemoval() {
        Product aProduct = new Product("aProduct", 1.5);
        theCustomer.addToCart(aProduct, 2);
        theCustomer.addToCart(aProduct, -3);
    }


}


