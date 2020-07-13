package values.either;

import io.vavr.control.Either;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EitherTest {

    @Test
    void eitherDemo(){
        Assertions.assertEquals(computeWithEither(50).getLeft(),"Marks not acceptable");
        Assertions.assertEquals(computeWithEither(100).get(),100);
    }

    private static Either<String, Integer> computeWithEither(int marks) {
        if (marks < 85) {
            return Either.left("Marks not acceptable");
        } else {
            return Either.right(marks);
        }
    }
}
