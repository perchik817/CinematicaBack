package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.OrderDetailsDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.OrderDetailsCreateRequest;
import megalab.cinematica.models.responces.Response;

public interface OrderDetailsService extends BaseService<OrderDetailsDto> {
    Response create(OrderDetailsCreateRequest request, Language lang);
}
