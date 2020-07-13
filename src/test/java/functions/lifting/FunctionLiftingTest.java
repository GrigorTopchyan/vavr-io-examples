package functions.lifting;

import io.vavr.Function2;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class FunctionLiftingTest {

    @Test
    void liftToTotalFunction(){
        Function2<Integer,Integer,Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivid = Function2.lift(divide);
        Option<Integer> none = safeDivid.apply(1, 0);
        Option<Integer> some2 = safeDivid.apply(4, 2);
        Assertions.assertTrue(none instanceof Option.None);
        Assertions.assertEquals(2, some2.get());

        Function2<Integer, Integer, Option<Integer>> safeSum = Function2.lift(this::sum);
        Option<Integer> sum = safeSum.apply(-1, 2);
        Assertions.assertTrue(sum instanceof Option.None);
    }


    private int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }
}
