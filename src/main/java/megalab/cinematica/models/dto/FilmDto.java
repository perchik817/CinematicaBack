package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;
import megalab.cinematica.models.enums.Genre;

import java.time.Duration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmDto extends BaseDto {
    Long id;
    String name;
    String logo;
    String definition;
    Genre genre;
    String ageRestrictions;
    String format;
    String duration;
}
