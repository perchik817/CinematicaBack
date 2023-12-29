package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.OrderDetailsDto;
import megalab.cinematica.models.requests.OrderDetailsCreateRequest;

public interface OrderDetailsService extends BaseService<OrderDetailsDto> {
    OrderDetailsDto create(OrderDetailsCreateRequest request);
}
