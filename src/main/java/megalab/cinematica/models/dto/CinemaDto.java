package megalab.cinematica.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.base.BaseDto;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaDto extends BaseDto {
    Long id;
    MultipartFile logo;
    String name;
    String definition;
    String address;
}
