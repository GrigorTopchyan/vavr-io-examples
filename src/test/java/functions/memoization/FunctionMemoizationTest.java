package functions.memoization;

import io.vavr.Function0;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FunctionMemoizationTest {

    @Test
    void memoizationDemo(){
        Function0<Double> hashCache = Function0.of(Math::random).memoized();
        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();
        Assertions.assertEquals(randomValue1,randomValue2);
    }
}
