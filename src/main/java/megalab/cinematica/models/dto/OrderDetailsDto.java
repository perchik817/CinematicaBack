package megalab.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailsDto extends BaseDto {
    OrderDto orderDto;
    SessionDto sessionDto;
    SeatsDto seatsDto;
    double price;
    int num;
}
