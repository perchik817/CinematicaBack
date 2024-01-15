package megalab.cinematica.models.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;
import megalab.cinematica.models.enums.SeatsStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatsDto extends BaseDto {
    int seat;
    SeatsStatus status;
    HallDto hall;
}
