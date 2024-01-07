package megalab.cinematica.models.responces;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmCinemaResponse {
    Long movieId;
    String image;
    String pg;
    String name;
}
