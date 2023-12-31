package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.HallRep;
import megalab.cinematica.exceptions.FindByIdException;
import megalab.cinematica.mappers.HallMapper;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.entity.Hall;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.HallCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.CinemaService;
import megalab.cinematica.service.HallService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl extends BaseServiceImpl<Hall, HallRep, HallDto, HallMapper> implements HallService {
    protected HallServiceImpl(HallRep repo, HallMapper mapper, CinemaService cinemaService) {
        super(repo, mapper);
        this.cinemaService = cinemaService;
    }

    private final CinemaService cinemaService;

    @Override
    public Response create(HallCreateRequest request, Language lang) {
        try{
            if(isNameUnique(request.getName())){
                CinemaDto cinemaDto = cinemaService.findById(request.getCinemaDto().getId(), lang);
                request.setCinemaDto(cinemaDto);

                HallDto hallDto = new HallDto();
                hallDto.setName(request.getName());
                hallDto.setCinemaDto(request.getCinemaDto());
                hallDto.setSeatsCount(request.getSeatsCount());
                mapper.toEntity(hallDto, context);
                return Response.getSuccessResponse(hallDto, lang);
            }else {
                return Response.getUniqueFieldResponse("notUniqueName", lang);
            }
        }catch (FindByIdException ex){
            throw new FindByIdException(ResourceBundle.periodMess("idNotFound", lang));
        }

    }
}
