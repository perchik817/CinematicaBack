package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.OrderDetailsRep;
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

import java.util.Objects;

@Service
public class OrderDetailsServiceImpl extends BaseServiceImpl<OrderDetails, OrderDetailsRep, OrderDetailsDto, OrderDetailsMapper>
        implements OrderDetailsService {


    private final OrderService orderService;
    private final SessionService sessionService;
    private final SeatsService seatsService;

    protected OrderDetailsServiceImpl(OrderDetailsRep repo, OrderDetailsMapper mapper, OrderService orderService, SessionService sessionService, SeatsService seatsService) {
        super(repo, mapper);
        this.orderService = orderService;
        this.sessionService = sessionService;
        this.seatsService = seatsService;
    }


    @Override
    public Response create(OrderDetailsCreateRequest request, Language lan) {
        try{
            OrderDto order = orderService.findById(request.getOrderId(), lan);
            SeatsDto seats = seatsService.findById(request.getSeatsId(), lan);
            SessionDto session = sessionService.findById(request.getSessionId(), lan);
            OrderDetailsDto orderDetails = new OrderDetailsDto();

            orderDetails.setOrder(order);
            orderDetails.setSession(session);
            orderDetails.setSeats(seats);
            if (session.getDiscount() > 0)
                request.setPrice(countPriceWithDisc(session.getPrice().getPrice(), session.getDiscount()));
            orderDetails.setPrice(request.getPrice());
            orderDetails.setNum(request.getNum());
            save(orderDetails);
            return Response.getSuccessResponse(orderDetails, lan);
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", lan));
        }
    }

    private double countPriceWithDisc(double price, double discount){
        price *= discount / 0.01;
        return price;
    }
}
