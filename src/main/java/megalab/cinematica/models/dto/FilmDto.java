package megalab.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;
import megalab.cinematica.models.enums.Genre;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto extends BaseDto {
    String name;
    MultipartFile logo;
    String definition;
    Genre genre;
    String format;
}
