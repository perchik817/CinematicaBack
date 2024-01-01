package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.OrderDetailsRep;
import megalab.cinematica.mappers.OrderDetailsMapper;
import megalab.cinematica.models.dto.OrderDetailsDto;
import megalab.cinematica.models.entity.OrderDetails;
import megalab.cinematica.models.requests.OrderDetailsCreateRequest;
import megalab.cinematica.service.OrderDetailsService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl extends BaseServiceImpl<OrderDetails, OrderDetailsRep, OrderDetailsDto, OrderDetailsMapper>
        implements OrderDetailsService {
    protected OrderDetailsServiceImpl(OrderDetailsRep repo, OrderDetailsMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public OrderDetailsDto create(OrderDetailsCreateRequest request) {
        return null;
    }
}
