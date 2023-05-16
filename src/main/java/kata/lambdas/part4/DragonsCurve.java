package kata.lambdas.part4;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class DragonsCurve {
    public IntFunction<String> mapFunction = c -> {
        if (c == 'a') return "aRbFR";
        if (c == 'b') return "LFaLb";
        return String.valueOf((char) c);
    };

    public String createCurve(int n) {
        String result = "Fa";
        for (int i = 0; i < n; i++) {
            result = result.chars().mapToObj(mapFunction).collect(Collectors.joining());
        }
        result = result.chars()
                .filter(createFilter('a', false))
                .filter(createFilter('b', false))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        return result;
    }

    public long howMany(char c, String curve) {
        return curve.chars()
                .filter(createFilter(c, true))
                .count();
    }

    public IntPredicate createFilter(char filterWhat, boolean keep) {
        return c -> (keep) ? c == filterWhat : c != filterWhat;
    }

    public static void main(String[] args) {
        DragonsCurve dc = new DragonsCurve();
        System.out.println(dc.createCurve(3));
    }
}
