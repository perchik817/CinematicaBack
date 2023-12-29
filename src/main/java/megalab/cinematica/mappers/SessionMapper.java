package megalab.cinematica.mappers;

import megalab.cinematica.base.BaseMapper;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.entity.Session;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface SessionMapper extends BaseMapper<Session, SessionDto> {
    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);
}
