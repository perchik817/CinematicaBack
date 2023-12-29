package megalab.cinematica.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;
import megalab.cinematica.models.enums.SeatsStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatsDto extends BaseDto {
    int num;
    int row;
    SeatsStatus status;
    HallDto hallDto;
}
