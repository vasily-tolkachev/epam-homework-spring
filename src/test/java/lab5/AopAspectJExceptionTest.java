package lab5;

import lab4.model.Person;
import lab5.model.Bar;
import lab5.model.CustomerBrokenException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ContextConfiguration(classes = lab5.configuration.ApplicationTestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AopAspectJExceptionTest {
    @NonNull
    Bar bar;

    @NonNull
    Person person;

    @BeforeEach
    void initial() {
        person = person.withBroke(true);
    }

    @Test
    void test() {
        String fromSystemOut = TestUtils.fromSystemOut(() -> Assertions.assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(person)));
        Assertions.assertTrue(fromSystemOut.contains("You have no money. We not happy to see you!"), "Before advice is not good");
    }
}
