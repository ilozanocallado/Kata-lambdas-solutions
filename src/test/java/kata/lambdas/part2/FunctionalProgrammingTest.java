package kata.lambdas.part2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FunctionalProgrammingTest {
    @Test
    public void testArea() throws Exception {
        Triangle t = new Triangle(5, 5);
        assertEquals("Incorrect area returned", 12.5, FunctionalProgramming.f.applyAsDouble(t), 0.001);
        assertEquals("Incorrect area in Triangle object", 12.5, t.getArea(), 0.001);
    }
}