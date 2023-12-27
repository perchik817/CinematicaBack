package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.base.CycleAvoidingMappingContext;
import megalab.cinematica.mappers.CinemaMapper;
import megalab.cinematica.microservices.FileService;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.dao.rep.CinemaRep;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.service.CinemaService;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaRep, CinemaDto, CinemaMapper> implements CinemaService{

    protected CinemaServiceImpl(CinemaRep cinemaRep, CinemaMapper mapper, FileService fileService) {
        super(cinemaRep, mapper);
        this.fileService = fileService;
    }

    private final FileService fileService;

    @Override
    public CinemaDto create(CinemaCreateRequest request) {
        //TODO add logo
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setLogo();
        cinemaDto.setName(request.getName());
        cinemaDto.setDefinition(request.getDefinition());
        cinemaDto.setAddress(request.getAddress());
        mapper.toEntity(cinemaDto, context);

        return null;//TODO response
    }
}
