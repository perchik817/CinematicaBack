package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.entity.Film;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface FilmMapper extends BaseMapper<Film, FilmDto> {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
}
