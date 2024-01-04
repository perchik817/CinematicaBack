package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.OrderDetailsDto;
import megalab.cinematica.models.entity.OrderDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface OrderDetailsMapper extends BaseMapper<OrderDetails, OrderDetailsDto> {
    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);
}
