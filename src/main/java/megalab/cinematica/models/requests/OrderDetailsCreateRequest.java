package megalab.cinematica.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.dto.SessionDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailsCreateRequest {

    Long orderId;
    Long sessionId;
    Long seatsId;
    double price;
    int num;
}
