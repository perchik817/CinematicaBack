package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.HallRep;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.CinemaMapper;
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
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl extends BaseServiceImpl<Hall, HallRep, HallDto, HallMapper> implements HallService {
    protected HallServiceImpl(HallRep repo, HallMapper mapper, CinemaService cinemaService, CinemaMapper cinemaMapper) {
        super(repo, mapper);
        this.cinemaService = cinemaService;
        this.cinemaMapper = cinemaMapper;
    }

    private final CinemaService cinemaService;
    private final CinemaMapper cinemaMapper;

    @Override
    public Response create(HallCreateRequest request, Language lang) {

        try{

            if (repo.findById(request.getCinemaId()) != null && !isHallNameUnique(request.getName())) {
                HallDto hallDto = new HallDto();
                hallDto.setName(request.getName());
                CinemaDto cinemaDto = cinemaService.findById(request.getCinemaId(), lang);
                hallDto.setCinemaDto(cinemaMapper.toDto(cinemaMapper.toEntity(cinemaDto, context), context));
                hallDto.setSeatsCount(request.getSeatsCount());
                save(hallDto);
                return Response.getSuccessResponse(hallDto, lang);
            } else {
                return Response.getUniqueFieldResponse("notUniqueName", lang);
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", lang));
        }
    }

    public boolean isHallNameUnique(String hallName) {
        return repo.existsByName(hallName);
    }
}
