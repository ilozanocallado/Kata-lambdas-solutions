package kata.lambdas.part2;

import java.util.function.ToDoubleFunction;

public class FunctionalProgramming {
    //Make me a function.
    //Don't use Function. Find the appropriate function to turn an
    //arbitrary class into a double. Remember the import.
    public static ToDoubleFunction<Triangle> f = triangle -> {
        triangle.setArea(0.5*triangle.height*triangle.base);
        return triangle.getArea();
    };

}
