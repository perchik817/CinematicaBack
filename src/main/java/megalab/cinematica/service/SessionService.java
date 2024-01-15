package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SessionCreateRequest;
import megalab.cinematica.models.responces.Response;

import java.time.LocalDate;
import java.util.List;

public interface SessionService extends BaseService<SessionDto> {
    Response create(SessionCreateRequest request, Language language);

    List<SessionDto> findByHallAndDate(Long hallId, LocalDate date, Long movieId);
}
