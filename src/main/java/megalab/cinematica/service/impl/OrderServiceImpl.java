package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.base.CycleAvoidingMappingContext;
import megalab.cinematica.dao.rep.OrderRep;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.OrderMapper;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.entity.Order;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.OrderCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.OrderService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDto, OrderMapper> implements OrderService {

    protected OrderServiceImpl(OrderRep repo, OrderMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public Response create(OrderCreateRequest request, Language language) {
        try{
            OrderDto orderDto = new OrderDto();
            orderDto.setTotalPrice(request.getTotalPrice());
            orderDto.setNum(request.getNum());
            mapper.toEntity(orderDto, context);
            return Response.getSuccessResponse(orderDto, language);
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }
}
