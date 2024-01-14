package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class HallDto extends BaseDto {
    Long id;
    String name;
    CinemaDto cinema;
    String seatsCount;
    String freeSeatsCount;
}
