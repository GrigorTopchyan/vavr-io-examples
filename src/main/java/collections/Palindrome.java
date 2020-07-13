package collections;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Vector;
import io.vavr.control.Option;

public class Palindrome {

    public static Vector<Tuple2<Integer, Integer>> substringPalindromes(String s) {
        int n = s.length();
        return Vector.range(0, n - 1)
                .flatMap(i -> Vector.of(Tuple.of(i, i + 1), Tuple.of(i, i + 2)))
                .flatMap(startIdx -> Vector.unfold(startIdx, currIdx -> {
                    final int i = currIdx._1;
                    final int j = currIdx._2;
                    if (0 <= i && j < n && isPalindrome(s, i, j)) {
                        Tuple2<Integer, Integer> nextIdx = Tuple.of(i - 1, j + 1);
                        return Option.some(Tuple.of(nextIdx, currIdx));
                    } else {
                        return Option.none();
                    }
                }));
    }

    private static boolean isPalindrome(String s, int i, int j) {
        return s.charAt(i) == s.charAt(j);
    }
}
