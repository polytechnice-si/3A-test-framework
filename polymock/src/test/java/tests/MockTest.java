package tests;

import fr.unice.polytech.qgl.tests.unit.annotations.*;
import sun.misc.ProxyGenerator;

import static fr.unice.polytech.qgl.tests.mock.Mock.*;
import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

@TestSuite
public class MockTest {

    interface Adder {
        int add(int a, int b);
    }

    private Adder anAdder;
    private Adder aMock;

    @Before public void setUp() {
        anAdder     = new Adder() { @Override public int add(int a, int b) { return a + b; } };
        aMock       = mock(Adder.class);
    }

    @After public void tearDown() {
        anAdder = null;
        aMock = null;
    }

    @Test(expected = RuntimeException.class)
    public void refuseToMockARealObject() {
        calling(anAdder, "add", 1,3);
    }

    @Test(expected = RuntimeException.class)
    public void unconfiguredMockThrowsException() {
        aMock.add(1,2);
    }

    @Test public void mockSupportDefaultValue() {
        calling(aMock,"add").defaultsTo(42);
        assertEquals(42, aMock.add(1,2));
        assertEquals(42, aMock.add(10,20));
    }

    @Test public void mockSupportMethodPartialDefinitions() {
        calling(aMock,"add", 1,2).returns(3);
        calling(aMock,"add", 3,5).returns(8);
        calling(aMock,"add").defaultsTo(42);
        assertEquals(3, aMock.add(1,2));
        assertEquals(8, aMock.add(3,5));
        assertEquals(42, aMock.add(5,3));
    }

    @Test public void mockAtTheObjectLevel() {
        Adder anotherMock = mock(Adder.class);
        calling(aMock,"add", 1,2).returns(3);
        calling(anotherMock,"add", 1,2).returns(8);
        calling(aMock,"add").defaultsTo(42);
        calling(anotherMock,"add").defaultsTo(-1);
        assertEquals(3, aMock.add(1,2));
        assertEquals(8, anotherMock.add(1,2));
        assertEquals(42, aMock.add(5,3));
        assertEquals(-1, anotherMock.add(5,3));
    }

}
