package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto extends BaseDto {
    Long id;
    double totalPrice;
    int num;
}
