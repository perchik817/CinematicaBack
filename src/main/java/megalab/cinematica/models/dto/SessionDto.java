package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionDto extends BaseDto {
    Long id;
    FilmDto filmDto;
    HallDto hallDto;
    Date dateTime;
    double price;
    double discount;
}
