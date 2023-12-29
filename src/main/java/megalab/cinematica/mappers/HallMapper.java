package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.entity.Hall;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface HallMapper extends BaseMapper<Hall, HallDto> {
    HallMapper INSTANCE = Mappers.getMapper(HallMapper.class);
}
