package megalab.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto extends BaseDto {
    FilmDto filmDto;
    HallDto hallDto;
    Date dateTime;
    double price;
    double discount;
}
