package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.requests.OrderCreateRequest;

public interface OrderService extends BaseService<OrderDto> {
    OrderDto create (OrderCreateRequest request);
}
