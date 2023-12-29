package megalab.cinematica.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.dto.HallDto;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionCreateRequest {
    FilmDto filmDto;
    HallDto hallDto;
    Date dateTime;
    double price;
    double discount;
}
