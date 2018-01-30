import fr.unice.polytech.qgl.tests.unit.Runner;

public class MockRunner {

    public static void main(String[] args) {
        Runner unitRunner = new Runner("demo.mock");
        unitRunner.execute();
    }

}
