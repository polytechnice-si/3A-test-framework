import fr.unice.polytech.qgl.tests.unit.assertions.AssertionFailedError;

import static fr.unice.polytech.qgl.tests.unit.assertions.Assertion.*;

public class OldSchoolMain {

    public static void main(String[] args) {
        failShouldFail();
        assertTrueWorks();
        assertFalseWorks();
        assertEqualsWorks();
        failingTest();
    }

    private static void failShouldFail() {
        System.out.print("Test #1: fail: ");
        boolean passed = false;
        try {
            fail("This one should fail");
            passed = true;
        } catch (AssertionFailedError e) {}
        if(!passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }
    }

    private static void assertTrueWorks() {
        System.out.print("Test #2.1: assertTrue: ");
        boolean passed = false;
        try {
            assertTrue( 1 == 1);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }

        System.out.print("Test #2.2: assertTrue: ");
        passed = false;
        try {
            assertTrue( 1 == 2);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(!passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }
    }

    private static void assertFalseWorks() {
        System.out.print("Test #3.1: assertFalse: ");
        boolean passed = false;
        try {
            assertFalse( 1 == 2);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }

        System.out.print("Test #3.2: assertFalse: ");
        passed = false;
        try {
            assertFalse( 1 == 1);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(!passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }
    }

    private static void assertEqualsWorks() {
        System.out.print("Test #4.1: assertEquals: ");
        boolean passed = false;
        try {
            assertEquals("pouet", "pouet");
            passed = true;
        } catch (AssertionFailedError e) {}
        if(passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }

        System.out.print("Test #4.2: assertEquals: ");
        passed = false;
        try {
            assertEquals( null, null);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }

        System.out.print("Test #4.3: assertEquals: ");
        passed = false;
        try {
            assertEquals( null, "pouet");
            passed = true;
        } catch (AssertionFailedError e) {}
        if(!passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }

        System.out.print("Test #4.4: assertEquals: ");
        passed = false;
        try {
            assertEquals( "pouet", null);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(!passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }

        System.out.print("Test #4.5: assertEquals: ");
        passed = false;
        try {
            assertEquals( "pouet", "pouetpouet");
            passed = true;
        } catch (AssertionFailedError e) {}
        if(!passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }
    }

    private static void failingTest() {
        System.out.print("Test #5: failure: ");
        boolean passed = false;
        try {
            assertEquals( 1, 2);
            passed = true;
        } catch (AssertionFailedError e) {}
        if(passed) { System.out.println("OK"); } else { System.out.println("FAILURE"); }
    }

}
