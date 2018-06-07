package lab4.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Builder
@Value
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("country")
public class SimpleCountry implements Country {
    long id;
    String name;
    String codeName;
}
