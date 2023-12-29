package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.entity.Price;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface PriceMapper extends BaseMapper<Price, PriceDto> {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
}
