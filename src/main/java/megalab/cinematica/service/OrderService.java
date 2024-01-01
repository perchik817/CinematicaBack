package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.OrderCreateRequest;
import megalab.cinematica.models.responces.Response;

public interface OrderService extends BaseService<OrderDto> {
    Response create (OrderCreateRequest request, Language language);
}
