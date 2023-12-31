package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.HallCreateRequest;
import megalab.cinematica.models.responces.Response;

public interface HallService extends BaseService<HallDto> {
    Response create(HallCreateRequest request, Language lang);
}
