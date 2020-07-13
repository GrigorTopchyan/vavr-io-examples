package values.lazy;

import io.vavr.Function0;
import io.vavr.Lazy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LazyTest {

    @Test
    void lazyDemo(){
        Lazy<Integer> lazyResult = lazyCompute();
        boolean evaluated = lazyResult.isEvaluated();
        Assertions.assertFalse(evaluated);
        Integer result = lazyResult.get();
        Assertions.assertTrue(lazyResult.isEvaluated());
        Assertions.assertEquals(1,result);
        result = lazyResult.get();
        Assertions.assertEquals(1,result);
    }

    private Lazy<Integer> lazyCompute(){
        Function0<Integer> compute = () -> {
            System.out.println("heavy computation");
            sleep(1000);
            return 1;
        };
        return Lazy.of(compute);
    }

    private void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
