package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.mappers.CinemaMapper;
import megalab.cinematica.microservices.FileServiceFeign;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.dao.rep.CinemaRep;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.CinemaService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaRep, CinemaDto, CinemaMapper> implements CinemaService{

    protected CinemaServiceImpl(CinemaRep cinemaRep, CinemaMapper mapper, FileServiceFeign fileService) {
        super(cinemaRep, mapper);
        this.fileService = fileService;
    }

    private final FileServiceFeign fileService;

    @Override
    public Response create(CinemaCreateRequest request, Language language) {
        //TODO add logo
        CinemaDto cinemaDto = new CinemaDto();
        //cinemaDto.setLogo();
        cinemaDto.setName(request.getName());
        cinemaDto.setDefinition(request.getDefinition());
        cinemaDto.setAddress(request.getAddress());
        mapper.toEntity(cinemaDto, context);

        return Response.getSuccessResponse(cinemaDto, language);
    }
}
