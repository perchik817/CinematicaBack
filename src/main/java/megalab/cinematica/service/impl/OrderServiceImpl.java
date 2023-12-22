package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.OrderRep;
import megalab.cinematica.models.Order;
import megalab.cinematica.service.OrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep> implements OrderService {

    public OrderServiceImpl(OrderRep orderRep) {
        super(orderRep);
    }
}
