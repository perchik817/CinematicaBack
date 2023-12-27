package megalab.cinematica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import megalab.cinematica.base.BaseDto;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDto extends BaseDto {
    MultipartFile logo;
    String name;
    String definition;
    String address;
}
