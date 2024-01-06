package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.OrderDetailsRep;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.OrderDetailsMapper;
import megalab.cinematica.models.dto.OrderDetailsDto;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.entity.OrderDetails;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.OrderDetailsCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.OrderDetailsService;
import megalab.cinematica.service.OrderService;
import megalab.cinematica.service.SeatsService;
import megalab.cinematica.service.SessionService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl extends BaseServiceImpl<OrderDetails, OrderDetailsRep, OrderDetailsDto, OrderDetailsMapper>
        implements OrderDetailsService {

    protected OrderDetailsServiceImpl(OrderDetailsRep repo, OrderDetailsMapper mapper, OrderService orderService, SessionService sessionService, SeatsService seatsService) {
        super(repo, mapper);
        this.orderService = orderService;
        this.sessionService = sessionService;
        this.seatsService = seatsService;
    }

    private final OrderService orderService;
    private final SessionService sessionService;
    private final SeatsService seatsService;


    @Override
    public Response create(OrderDetailsCreateRequest request, Language lan) {
        try{
            OrderDto orderDto = orderService.findById(request.getOrderId(), lan);
            SeatsDto seatsDto = seatsService.findById(request.getSeatsId(), lan);
            SessionDto sessionDto = sessionService.findById(request.getSessionId(), lan);
            OrderDetailsDto orderDetailsDto = new OrderDetailsDto();

            orderDetailsDto.setOrderDto(orderDto);
            orderDetailsDto.setSessionDto(sessionDto);
            orderDetailsDto.setSeatsDto(seatsDto);
            if(sessionDto.getDiscount() > 0) request.setPrice(countPriceWithDisc(sessionDto.getPriceDto().getPrice(),
                    sessionDto.getDiscount()));
            orderDetailsDto.setPrice(request.getPrice());
            orderDetailsDto.setNum(request.getNum());
            save(orderDetailsDto);
            return Response.getSuccessResponse(orderDetailsDto, lan);
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", lan));
        }
    }

    private double countPriceWithDisc(double price, double discount){
        price *= discount / 0.01;
        return price;
    }
}
