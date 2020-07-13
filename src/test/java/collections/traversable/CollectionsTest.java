package collections.traversable;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionsTest {

    @Test
    void listDemo(){
        List<String> list = List.of("Java", "Kotlin", "Scala", "JavaScript", "JShell", "Rust");
        List<String> list1 = list.drop(2);
        Assertions.assertFalse(list1.contains("Java") && list1.contains("Kotlin"));
        Assertions.assertTrue(list.contains("Java") && list.contains("Kotlin"));

        list1 = list.dropRight(2);
        Assertions.assertFalse(list1.contains("JShell") && list1.contains("Rust"));

        list1 = list.dropUntil(s -> s.contains("R"));
        Assertions.assertEquals(1, list1.size());

        list1 = list.dropWhile(s -> s.length() > 1);
        Assertions.assertTrue(list1.isEmpty());
    }

    @Test
    void queueDemo(){
        Queue<Integer> queue = Queue.of(1, 2);
        Queue<Integer> queue1 = queue.enqueueAll(List.of(3, 4));
        Assertions.assertEquals(queue.size(), 2);
        Assertions.assertEquals(queue1.size(),4);

        Tuple2<Integer, Queue<Integer>> result = queue1.dequeue();
        Assertions.assertEquals(result._1,1);
        Assertions.assertEquals(result._2.size(),3);
    }

    @Test
    void vectorDemo(){
        Vector<Integer> vector = Vector.unfold(10, x -> x == 0 ? Option.none() : Option.some(Tuple.of(x-1, x)));
        Assertions.assertTrue(Vector.rangeClosed(1,10).forAll(vector::contains));
    }
}
