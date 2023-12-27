package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.entity.Cinema;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface CinemaMapper extends BaseMapper<Cinema, CinemaDto> {
    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);
}
