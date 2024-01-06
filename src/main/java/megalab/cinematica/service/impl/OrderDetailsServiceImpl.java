package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.OrderDetailsRep;
import megalab.cinematica.exceptions.FindByIdException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.OrderDetailsMapper;
import megalab.cinematica.mappers.OrderMapper;
import megalab.cinematica.mappers.SeatsMapper;
import megalab.cinematica.mappers.SessionMapper;
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

    protected OrderDetailsServiceImpl(OrderDetailsRep repo, OrderDetailsMapper mapper, OrderService orderService, OrderMapper orderMapper, SessionService sessionService, SessionMapper sessionMapper, SeatsService seatsService, SeatsMapper seatsMapper) {
        super(repo, mapper);
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
        this.seatsService = seatsService;
        this.seatsMapper = seatsMapper;
    }

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;
    private final SeatsService seatsService;
    private final SeatsMapper seatsMapper;


    @Override
    public Response create(OrderDetailsCreateRequest request, Language lan) {
        try{
            if(repo.findById(request.getOrderId()) != null
                    && repo.findById(request.getSeatsId()) != null
                    && repo.findById(request.getSessionId()) != null) {

                OrderDto orderDto = orderService.findById(request.getOrderId(), lan);
                SeatsDto seatsDto = seatsService.findById(request.getSeatsId(), lan);
                SessionDto sessionDto = sessionService.findById(request.getSessionId(), lan);
                OrderDetailsDto orderDetailsDto = new OrderDetailsDto();

                orderDetailsDto.setOrderDto(orderMapper.toDto(orderMapper.toEntity(orderDto, context), context));
                orderDetailsDto.setSessionDto(sessionMapper.toDto(sessionMapper.toEntity(sessionDto, context), context));
                orderDetailsDto.setSeatsDto(seatsMapper.toDto(seatsMapper.toEntity(seatsDto, context), context));
                if (sessionDto.getDiscount() > 0)
                    request.setPrice(countPriceWithDisc(sessionDto.getPriceDto().getPrice(), sessionDto.getDiscount()));
                orderDetailsDto.setPrice(request.getPrice());
                orderDetailsDto.setNum(request.getNum());
                save(orderDetailsDto);
                return Response.getSuccessResponse(orderDetailsDto, lan);
            } else {
                return Response.getUniqueFieldResponse("idNotFound", lan);
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", lan));
        }
    }

    private double countPriceWithDisc(double price, double discount){
        price *= discount / 0.01;
        return price;
    }
}
