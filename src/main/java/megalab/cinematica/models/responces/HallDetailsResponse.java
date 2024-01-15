package megalab.cinematica.models.responces;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallDetailsResponse {
    String name;
    List<SessionDetailsResponse> roomMovieId;
}
