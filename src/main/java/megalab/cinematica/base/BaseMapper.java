package megalab.cinematica.base;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public interface BaseMapper <ENTITY extends BaseEntity, DTO extends BaseDto>{
    ENTITY toEntity(DTO dto, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    DTO toDto(ENTITY entity, @Context CycleAvoidingMappingContext context);

    List<ENTITY> toEntities(List<DTO> dtos, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    List<DTO> toDtos(List<ENTITY> entities, @Context CycleAvoidingMappingContext context);
}
