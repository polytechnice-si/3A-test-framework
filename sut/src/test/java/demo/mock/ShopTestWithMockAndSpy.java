package demo.mock;

import fr.unice.polytech.qgl.tests.shop.*;
import fr.unice.polytech.qgl.tests.unit.annotations.*;

import java.util.Optional;

import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;
import static fr.unice.polytech.qgl.tests.mock.Mock.*;
import static fr.unice.polytech.qgl.tests.mock.Spy.*;

@TestSuite
public class ShopTestWithMockAndSpy {

    private static final Product aProduct = new Product("aProduct", 42.0);
    private static final Product anotherProduct = new Product("anotherProduct", 1.5);

    private Shop theShop;
    @Before public void initialize() {
        Catalogue testCatalogue = mock(Catalogue.class);
        calling(testCatalogue, "findByBarCode", "42")
                .returns(Optional.of(aProduct));
        calling(testCatalogue, "findByBarCode", "666")
                .returns(Optional.of(anotherProduct));
        calling(testCatalogue, "findByBarCode")
                .defaultsTo(Optional.empty());
        Bank theBank = spy(Bank.class);

        theShop = new Shop(testCatalogue, theBank);
    }

    @After public void cleanup()     { this.theShop = null; }

    @Test public void theMockedCatalogueDoItsJob() {
        assertEquals(aProduct, theShop.retrieveAProduct("42"));
        assertEquals(anotherProduct, theShop.retrieveAProduct("666"));
    }

    @Test(expected = RuntimeException.class) public void theMockedCatalogueDoItsJobWhenDefaulting() {
        theShop.retrieveAProduct("UNKNOWN");
    }

}


