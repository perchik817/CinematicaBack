package megalab.cinematica.models.requests;


import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.enums.SeatsStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatsCreateRequest {
    Long id;
    int num;
    int row;
    SeatsStatus status;
    HallDto hallDto;
}
