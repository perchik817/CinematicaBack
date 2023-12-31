package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.responces.Response;

public interface CinemaService extends BaseService<CinemaDto> {
    Response create(CinemaCreateRequest request, Language language);
}
