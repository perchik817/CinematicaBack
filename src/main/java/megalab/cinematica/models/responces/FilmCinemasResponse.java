package megalab.cinematica.models.responces;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmCinemasResponse {
    String image;
    String def;
    String pg;
    String name;
    List<CinemasResponse> cinemas;
}
