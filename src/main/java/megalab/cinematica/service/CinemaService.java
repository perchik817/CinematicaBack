package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.models.dto.CinemaDto;

public interface CinemaService extends BaseService<CinemaDto> {
    CinemaDto create(CinemaCreateRequest request);
}
