package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.entity.Seats;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface SeatsMapper extends BaseMapper<Seats, SeatsDto> {
    SeatsMapper INSTANCE = Mappers.getMapper(SeatsMapper.class);
}
