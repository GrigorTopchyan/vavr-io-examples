package collections.traversable;

import collections.Palindrome;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalindromeTest {

    @Test
    public void whenGivenString_ThenReturnAllSubstringPalindromes(){
        Vector<Tuple2<Integer, Integer>> substringPalindromes = Palindrome.substringPalindromes("sabbath");
        Assertions.assertTrue(substringPalindromes.contains(Tuple.of(1,4)) && substringPalindromes.contains(Tuple.of(2,3)));
    }
}