package lab4.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@AllArgsConstructor
@Value
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("person")
public class SimplePerson implements Person {

    long id;
    String firstName;
    String lastName;
    Country country;
    int age;
    float height;
    boolean isProgrammer;

    @Wither
    boolean broke;

    @Singular
    List<Contact> contacts;
}
