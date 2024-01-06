package megalab.cinematica.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.PriceDto;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionCreateRequest {

    Long filmId;
    Long hallId;
    Long priceId;
    Date dateTime;
    double discount;
}
