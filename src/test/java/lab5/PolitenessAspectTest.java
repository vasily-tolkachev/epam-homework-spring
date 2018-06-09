package lab5;

import lab4.model.Person;
import lab5.model.ApuBar;
import lab5.model.Bar;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ContextConfiguration(classes = lab5.configuration.ApplicationTestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PolitenessAspectTest {
    Bar bar;
    Person person;

    @NonFinal
    String fromSystemOut;

    @BeforeEach
    void beforeEach() {
        fromSystemOut = TestUtils.fromSystemOut(() -> bar.sellSquishee(person));
    }

    @Test
    void beforeAdviceTest() {
        Assertions.assertTrue(fromSystemOut.contains("How are you doing " + person.getName()), "Before advice is not good");
    }

    @Test
    void testAfterAdvice() {
        Assertions.assertTrue(fromSystemOut.contains("Have a nice day!"), "After advice is not good");
    }

    @Test
    void testAfterReturningAdvice() {
        Assertions.assertTrue(fromSystemOut.contains("Good Enough?"), "Customer is broken");
    }

    @Test
    void testAroundAdvice() {
        Assertions.assertTrue(fromSystemOut.contains("Hi!"), "Around advice is not good");
        Assertions.assertTrue(fromSystemOut.contains("See you!"), "Around advice is not good");
    }

    @Test
    void testAllAdvices() {
        Assertions.assertFalse(bar instanceof ApuBar, "Object is instanceof ApuBar");
    }
}
