package megalab.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;
import megalab.cinematica.models.enums.Ticket;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceDto extends BaseDto {
    double price;
    Ticket type;
}
