package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.base.CycleAvoidingMappingContext;
import megalab.cinematica.dao.rep.OrderRep;
import megalab.cinematica.mappers.OrderMapper;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.entity.Order;
import megalab.cinematica.models.requests.OrderCreateRequest;
import megalab.cinematica.service.OrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDto, OrderMapper> implements OrderService {

    protected OrderServiceImpl(OrderRep repo, OrderMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public OrderDto create(OrderCreateRequest request) {
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalPrice(request.getTotalPrice());
        orderDto.setNum(request.getNum());
        mapper.toEntity(orderDto, context);
        return null;
    }
}
