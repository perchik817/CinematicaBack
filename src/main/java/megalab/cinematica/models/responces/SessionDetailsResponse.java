package megalab.cinematica.models.responces;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionDetailsResponse {
    Long id;
    Double standardPrice;
    Double childPrice;
    Double studentPrice;
    Date startTime;
}
