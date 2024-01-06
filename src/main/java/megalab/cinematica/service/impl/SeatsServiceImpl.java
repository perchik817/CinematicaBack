package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SeatsRep;
import megalab.cinematica.exceptions.FindByIdException;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.PlaceIsOccupied;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.SeatsMapper;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.entity.Seats;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.enums.SeatsStatus;
import megalab.cinematica.models.requests.SeatsCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.HallService;
import megalab.cinematica.service.SeatsService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class SeatsServiceImpl extends BaseServiceImpl<Seats, SeatsRep, SeatsDto, SeatsMapper> implements SeatsService {
    protected SeatsServiceImpl(SeatsRep repo, SeatsMapper mapper, HallService hallService) {
        super(repo, mapper);
        this.hallService = hallService;
    }
    private final HallService hallService;

    @Override
    public Response create(SeatsCreateRequest request, Language language) {
        try {
            if (request.getRow() > 0 && request.getNum() > 0) {
                if (placeIsFree(request.getStatus())) {
                    HallDto hallDto = hallService.findById(request.getHallDto().getId(), language);
                    request.setHallDto(hallDto);
                    SeatsDto seatsDto = new SeatsDto();

                    seatsDto.setNum(request.getNum());
                    seatsDto.setRow(request.getRow());
                    seatsDto.setStatus(request.getStatus());
                    seatsDto.setHallDto(request.getHallDto());
                    save(seatsDto);
                    return Response.getSuccessResponse(seatsDto, language);
                } else {
                    throw new PlaceIsOccupied(ResourceBundle.periodMess("placeIsOccupied", language));
                }
            }else{
                throw new NumException(ResourceBundle.periodMess("placeNumIsNegative", language));
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

    private boolean placeIsFree(SeatsStatus status){
        return status.equals(SeatsStatus.FREE);
    }
}
