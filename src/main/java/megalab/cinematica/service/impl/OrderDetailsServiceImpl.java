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
            OrderDto orderDto = orderService.findById(request.getOrderDto().getId(), lan);
            SeatsDto seatsDto = seatsService.findById(request.getSeatsDto().getId(), lan);
            SessionDto sessionDto = sessionService.findById(request.getSessionDto().getId(), lan);
            OrderDetailsDto orderDetailsDto = new OrderDetailsDto();

            request.setOrderDto(orderDto);
            request.setSessionDto(sessionDto);
            request.setSeatsDto(seatsDto);

            orderDetailsDto.setOrderDto(request.getOrderDto());
            orderDetailsDto.setSessionDto(request.getSessionDto());
            orderDetailsDto.setSeatsDto(request.getSeatsDto());
            orderDetailsDto.setPrice(request.getPrice());
            orderDetailsDto.setNum(request.getNum());

            mapper.toEntity(orderDetailsDto, context);
            save(orderDetailsDto);
            return Response.getSuccessResponse(orderDetailsDto, lan);
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", lan));
        }
    }
}
