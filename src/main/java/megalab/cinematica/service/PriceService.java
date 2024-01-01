package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.PriceCreateRequest;
import megalab.cinematica.models.responces.Response;

public interface PriceService extends BaseService<PriceDto> {
    Response create(PriceCreateRequest request, Language language);
}
