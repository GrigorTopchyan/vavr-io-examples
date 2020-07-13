package functions.currying;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FunctionCurryingTest {

    @Test
    void curryingFunction2(){
        Function2<Integer,Integer,Integer> sum = (a,b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);
        Assertions.assertEquals(6,add2.apply(4));
    }

    @Test
    void curryingHigherArityFunctions(){
        Function3<Integer,Integer,Integer,Integer> sum = (a,b,c) -> a + b + c;
        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> curried = sum.curried();
        Function1<Integer, Function1<Integer, Integer>> add2 = curried.apply(2);
        Function1<Integer, Integer> add2_4 = add2.apply(4);
        Integer result = add2_4.apply(3);
        Assertions.assertEquals(9,result);
        Assertions.assertEquals(9,
                curried.apply(2)
                .apply(4)
                .apply(3)
        );
    }
}
