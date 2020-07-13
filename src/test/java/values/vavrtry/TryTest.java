package values.vavrtry;

import io.vavr.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.$;
import static io.vavr.Predicates.instanceOf;


public class TryTest {

    @Test
    void tryDemo() {
        Assertions.assertEquals(Try.of(this::bunchOfWork).getOrElse("Default"), "Result");
        Assertions.assertEquals(Try.of(this::bunchOfWorkWithException).getOrElse("Default"), "Default");

        String result = Try.of(this::bunchOfWorkWithException)
                .recover(
                        x -> Match(x).of(
                                Case($(instanceOf(Exception.class)), this::handleException),
                                Case($(instanceOf(RuntimeException.class)), this::handleRuntimeException)
                        ))
                .getOrElse("Default");

        Assertions.assertEquals(result,"Exception");
    }

    private String handleException(Exception t) {
        return "Exception";
    }

    private String handleRuntimeException(Exception t) {
        return "RuntimeException";
    }

    private String bunchOfWork() {
        return "Result";
    }

    private String bunchOfWorkWithException() throws Exception {
        throw new Exception("Processing Error");
    }

}
