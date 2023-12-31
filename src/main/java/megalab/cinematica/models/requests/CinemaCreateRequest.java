package megalab.cinematica.models.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaCreateRequest {
    Long id;
    MultipartFile logo;
    String name;
    String definition;
    String address;
}
