package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SessionRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.SessionMapper;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.entity.Hall;
import megalab.cinematica.models.entity.Session;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SessionCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.FilmService;
import megalab.cinematica.service.HallService;
import megalab.cinematica.service.PriceService;
import megalab.cinematica.service.SessionService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
public class SessionServiceImpl extends BaseServiceImpl<Session, SessionRep, SessionDto, SessionMapper>
        implements SessionService {
    protected SessionServiceImpl(SessionRep repo, SessionMapper mapper, FilmService filmService, HallService hallService, PriceService priceService) {
        super(repo, mapper);
        this.filmService = filmService;
        this.hallService = hallService;
        this.priceService = priceService;
    }

    private final FilmService filmService;
    private final HallService hallService;
    private final PriceService priceService;


    @Override
    public Response create(SessionCreateRequest request, Language language) {
        try{
            HallDto hallDto = hallService.findById(request.getHallId(), language);
            FilmDto filmDto = filmService.findById(request.getFilmId(), language);
            PriceDto priceDto = priceService.findById(request.getPriceId(), language);
            SessionDto sessionDto = new SessionDto();

            if(!areSessionsInSimilarHall(request, filmDto, hallDto)) {
                if (priceDto.getPrice() > 0 ) {
                    sessionDto.setHallDto(hallDto);
                    sessionDto.setFilmDto(filmDto);
                    sessionDto.setPriceDto(priceDto);
                    sessionDto.setDateTime(request.getDateTime());
                    sessionDto.setDiscount(request.getDiscount());
                    save(sessionDto);
                    return Response.getSuccessResponse(sessionDto, language);
                } else {
                    throw new NumException(ResourceBundle.periodMess("priceIsNegative", language));
                }
            }else{
                return Response.getErrorResponse("sessionsOverlap", language);
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

    private boolean areSessionsInSimilarHall(SessionCreateRequest request, FilmDto filmDto, HallDto hallDto){
        Date requestDateTime = request.getDateTime();
        List<SessionDto> sessionDtos = findAll();
        Duration filmDuration = filmDto.getDuration();

        for (SessionDto session : sessionDtos) {
            if (session.getHallDto().getId().equals(hallDto.getId())) {
                if (isSessionOverlap(requestDateTime, filmDuration, session.getDateTime(), filmDto.getDuration())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSessionOverlap(Date newSessionStart,
                                     Duration newSessionDuration,
                                     Date existingSessionStart,
                                     Duration existingSessionDuration) {
        long newSessionEndMillis = newSessionStart.getTime() + newSessionDuration.toMillis();
        long existingSessionEndMillis = existingSessionStart.getTime() + existingSessionDuration.toMillis();

        return (newSessionStart.before(existingSessionStart) &&
                newSessionEndMillis > existingSessionStart.getTime()) ||
                (newSessionStart.getTime() < existingSessionEndMillis &&
                        newSessionEndMillis > existingSessionEndMillis);
    }
}
