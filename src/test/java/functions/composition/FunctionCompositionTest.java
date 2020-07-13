package functions.composition;


import io.vavr.Function1;
import io.vavr.Function2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FunctionCompositionTest {

    @Test
    public void testFunctionCompose(){
        Function1<String,String> greet = s -> String.format("Hello %s",s);
        Function1<String,String> trim = String::trim;
        Function1<String, String > toUpper = String::toUpperCase;
        Assertions.assertEquals(greet.compose(trim).compose(toUpper).apply(" mike "),"Hello MIKE");
    }

    @Test
    public void testFunctionAndThen(){
        Function1<String,String> greet = s -> String.format("Hello %s",s);
        Function1<String,String> trim = String::trim;
        Function1<String, String > toUpper = String::toUpperCase;
        Assertions.assertEquals(trim.andThen(greet).andThen(toUpper).apply(" mike "),"HELLO MIKE");
    }

    @Test
    public void testPartialApplication(){
        Function2<String,String, String> greet = (s1,s2) -> String.format("%s %s",s1,s2);
        Function1<String,String> sayHello = greet.apply("Hello");
        Function1<String,String> sayHi = greet.apply("Hi");
        Assertions.assertEquals(sayHello.apply("Mike"),"Hello Mike");
        Assertions.assertEquals(sayHi.apply("Mike"),"Hi Mike");
    }
}
