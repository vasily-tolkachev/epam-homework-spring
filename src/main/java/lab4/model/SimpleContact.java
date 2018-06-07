package lab4.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleContact implements Contact {
    long id;
    String value;

    @Builder.Default
    String type;
}
