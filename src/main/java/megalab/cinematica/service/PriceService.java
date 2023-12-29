package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.requests.PriceCreateRequest;

public interface PriceService extends BaseService<PriceDto> {
    PriceDto create(PriceCreateRequest request);
}
