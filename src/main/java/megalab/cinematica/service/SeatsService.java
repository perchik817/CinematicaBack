package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SeatsCreateRequest;
import megalab.cinematica.models.responces.HallSeatsResponse;
import megalab.cinematica.models.responces.Response;

public interface SeatsService extends BaseService<SeatsDto> {
    Response create(SeatsCreateRequest request, Language language);
    HallSeatsResponse getHallSeats(Long id, Language lan);
    Response removeSeat(Long hallId, int selectedSeat, Language language);
}
