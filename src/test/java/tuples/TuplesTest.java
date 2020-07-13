package tuples;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TuplesTest {

    @Test
    void createTupleWithFactoryMethod(){
        Tuple2<String,Integer> java8 = Tuple.of("Java",8);
        Assertions.assertEquals("Java",java8._1);
        Assertions.assertEquals(8,java8._2);
    }

    @Test
    void tupleMethodsDemo(){
        Tuple2<String,Integer> java8 = Tuple.of("Java",8);

        //append
        Tuple3<String, Integer, String> java8Version = java8.append("version");
        Assertions.assertEquals("version",java8Version._3);

        //compareTo
        int compareValue = java8.compareTo(Tuple.of("Java", 7));
        Assertions.assertTrue(compareValue > 0);

        //map
        Tuple2<String, Integer> that = java8.map(s -> s.substring(2) + "vr", i -> i / 8);
        Assertions.assertEquals("vavr",that._1);
        Assertions.assertEquals(1,that._2);

        //map with one mapper
        that = java8.map((s,i) -> Tuple.of(s.substring(2) + "vr",i/8));
        Assertions.assertEquals(Tuple.of("vavr",1),that);

        //apply
        String vavr1 = java8.apply((s, i) -> s.substring(2) + "vr" + i / 8);
        Assertions.assertEquals("vavr1", vavr1);

        //concat
        Tuple4<String, Integer, String, Integer> java8Vavr1 = java8.concat(Tuple.of("vavr", 1));
        Assertions.assertEquals(Tuple.of("Java",8,"vavr", 1),java8Vavr1);

        //swap
        Tuple2<Integer, String> swap = java8.swap();
        Assertions.assertEquals(Tuple.of(8,"Java"),swap);

    }
}
