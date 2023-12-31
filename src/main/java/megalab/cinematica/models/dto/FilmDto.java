package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;
import megalab.cinematica.models.enums.Genre;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmDto extends BaseDto {
    Long id;
    String name;
    MultipartFile logo;
    String definition;
    Genre genre;
    String format;
    Duration duration;
}
