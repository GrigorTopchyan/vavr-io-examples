package values.future;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class FutureTest {

    @Test
    @DisplayName("when non blocking getValue called and result isn't completed then Option.None is returned")
    void futureGetValue(){
        Future<String> future = Future.of(this::doSomeComputation);
        Option<Try<String>> futureOption = future.getValue();
        Assertions.assertTrue(futureOption instanceof Option.None);
    }

    @Test
    @DisplayName("When executor service is passed then is run in that thread pool")
    void changeDefaultExecutorService() {
        Future<String> future = Future.of(newSingleThreadExecutor(),this::doSomeComputation);
        String result = future.getOrElse("Error");
        Assertions.assertEquals(result,"Result");

    }

    @Test
    void performActionOnSuccess(){
        Future<String> future = Future.of(this::doSomeComputation);
        future.onSuccess(res -> Assertions.assertEquals(res,"Result"));
        future.await();
    }

    @Test
    void performActionOnFailure(){
        Future<String> future = Future.of(this::doSomeComputationWithError);
        future.onFailure(ex -> Assertions.assertEquals(ex.getMessage(),"Error during computation"));
        future.await();
    }

    @Test
    void transformWithFlatMap(){
        Future<String> future = Future.of(this::doSomeComputation)
                .flatMap(res -> Future.of(() -> res + "is computed"));
        Assertions.assertEquals(future.get(),"Result is computed");
    }

    @Test
    void futureZip(){
        Future<String> future = Future.of(this::doSomeComputation);
        Future<String> future1 = Future.of(this::doOtherComputation);
        Future<Tuple2<String, String>> zip = future.zip(future1);
        Tuple2<String, String> tuple = zip.get();
        Assertions.assertEquals(tuple, Tuple.of("Result","Other result"));
    }

    @Test
    void whenFutureFails_ThenGetErrorMessage(){
        Future<String> future = Future.of(this::doSomeComputationWithError)
                .recover(Throwable::getMessage);
        Assertions.assertEquals(future.get(),"Error during computation");
    }

    @Test
    void whenFutureFails_ThenAnotherFuture(){
        Future<String> future = Future.of(this::doSomeComputationWithError).recoverWith(ex -> Future.of(this::doOtherComputation));
        Assertions.assertEquals(future.get(),"Other result");
    }

    private String doSomeComputation(){
        sleep(1000);
        System.out.println("Calculating result ...");
        return "Result";
    }

    private String doOtherComputation(){
        sleep(1000);
        System.out.println("Calculating result ...");
        return "Other result";
    }

    private String doSomeComputationWithError(){
        sleep(1000);
        System.out.println("Calculating result ...");
        throw new RuntimeException("Error during computation");
    }

    private void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
