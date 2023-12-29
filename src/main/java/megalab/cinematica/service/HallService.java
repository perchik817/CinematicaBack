package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.requests.HallCreateRequest;

public interface HallService extends BaseService<HallDto> {
    HallDto create(HallCreateRequest request);
}
