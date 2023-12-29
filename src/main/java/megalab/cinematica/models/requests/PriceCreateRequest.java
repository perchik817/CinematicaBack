package megalab.cinematica.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.enums.Ticket;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceCreateRequest {
    double price;
    Ticket type;
}
