package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.requests.SeatsCreateRequest;

public interface SeatsService extends BaseService<SeatsDto> {
    SeatsDto create(SeatsCreateRequest request);
}
