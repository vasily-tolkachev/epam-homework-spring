package lab4;

import lab4.configuration.ApplicationTestConfiguration;
import lab4.model.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ContextConfiguration(classes = ApplicationTestConfiguration.class)
@ExtendWith(SpringExtension.class)
public class SpringTCFApplicationTest {

    Person person;

    @Test
    void test() {
        assertEquals(person, ApplicationTest.getExpectedPerson());
    }
}
