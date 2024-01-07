package megalab.cinematica.models.responces;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import megalab.cinematica.models.enums.Ticket;

import java.util.Date;
import java.util.HashMap;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HallFilmId {
    Long id;
    Double standardPrice;
    Double childPrice;
    Date startTime;
}
