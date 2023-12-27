package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.entity.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}
