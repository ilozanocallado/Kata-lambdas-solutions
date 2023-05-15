package kata.lambdas.part1;

import java.util.function.Function;

public class FunctionalProgramming {
    //Make me a function; remember to set the types!
    public static Function<Student, Boolean> f = s -> "John Smith".equals(s.getFullName()) && "js123".equals(s.studentNumber);



}
