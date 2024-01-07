package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class HallDto extends BaseDto {
    String name;
    CinemaDto cinema;
    int seatsCount;
}
