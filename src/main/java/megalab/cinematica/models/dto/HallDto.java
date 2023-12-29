package megalab.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HallDto extends BaseDto {
    CinemaDto cinemaDto;
    int seatsCount;
}
