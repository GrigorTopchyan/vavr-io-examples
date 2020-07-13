package values.option;

import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionTest {

    @Test
    void optionDemo(){
        Option<String> maybeFoo = Option.of("foo");
        Assertions.assertEquals(maybeFoo.get(),"foo");
        Assertions.assertThrows(NullPointerException.class,
                () -> maybeFoo
                        .map(s -> (String)null)
                        .map(String::toUpperCase));

        Option<String> maybeFooBar = maybeFoo
                .flatMap(s -> Option.of((String) null))
                .map(String::toUpperCase);

        Assertions.assertTrue(maybeFooBar.isEmpty());
    }
}
