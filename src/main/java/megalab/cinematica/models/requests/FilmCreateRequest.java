package megalab.cinematica.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.enums.Genre;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmCreateRequest {

    String name;
    MultipartFile logo;
    String definition;
    Genre genre;
    String format;
    Duration duration;
}
