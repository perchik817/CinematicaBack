package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailsDto extends BaseDto {
    OrderDto orderDto;
    SessionDto sessionDto;
    SeatsDto seatsDto;
    double price;
    int num;
}
