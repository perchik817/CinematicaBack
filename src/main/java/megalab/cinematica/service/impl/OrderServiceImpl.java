package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.OrderRep;
import megalab.cinematica.exceptions.NumException;
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
            orderDto.setNum(Math.toIntExact(orderDto.getId()));
            orderDto.setTotalPrice(repo.findOrderDetailsByNum(orderDto.getNum()));
            save(orderDto);
            return Response.getSuccessResponse(orderDto, language);
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

//    @Override
//    public Response create(OrderCreateRequest request, Language language) {
//        try{
//            if(request.getTotalPrice() > 0) {
//                OrderDto orderDto = new OrderDto();
//                orderDto.setTotalPrice(request.getTotalPrice());
//                orderDto.setNum(request.getNum());
//                save(orderDto);
//                return Response.getSuccessResponse(orderDto, language);
//            }else {
//                throw new NumException(ResourceBundle.periodMess("priceIsNegative", language));
//            }
//        }catch (UnsavedDataException e){
//            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
//        }
//    }
}
