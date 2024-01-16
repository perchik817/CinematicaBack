package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.SessionRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.SessionMapper;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.dto.SessionDto;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SessionServiceImpl extends BaseServiceImpl<Session, SessionRep, SessionDto, SessionMapper>
        implements SessionService {


    private final FilmService filmService;
    private final HallService hallService;
    private final PriceService priceService;

    protected SessionServiceImpl(SessionRep repo, SessionMapper mapper, FilmService filmService, HallService hallService, PriceService priceService) {
        super(repo, mapper);
        this.filmService = filmService;
        this.hallService = hallService;
        this.priceService = priceService;
    }


    @Override
    public Response create(SessionCreateRequest request, Language language) {
        try{
            HallDto hall = hallService.findById(request.getHallId(), language);
            FilmDto film = filmService.findById(request.getFilmId(), language);
            PriceDto price = priceService.findById(request.getPriceId(), language);
            if(Objects.nonNull(hall) && Objects.nonNull(film) && Objects.nonNull(price)) {
                SessionDto sessionDto = new SessionDto();
                Duration filmDuration = filmService.parseDuration(film.getDuration());
                if (!areSessionsInSimilarHall(request, filmDuration, hall)) {
                    if (price.getPrice() > 0) {
                        sessionDto.setHall(hall);
                        sessionDto.setFilm(film);
                        sessionDto.setPrice(price);
                        sessionDto.setDateTime(request.getDateTime());
                        sessionDto.setDiscount(request.getDiscount());
                        save(sessionDto);
                        return Response.getSuccessResponse(sessionDto, language);
                    } else {
                        throw new NumException(ResourceBundle.periodMess("priceIsNegative", language));
                    }
                } else {
                    return Response.getErrorResponse("sessionsOverlap", language);
                }
            }else {
                return Response.getUniqueFieldResponse("idNotFound", language);
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }



    private boolean areSessionsInSimilarHall(SessionCreateRequest request, Duration filmDuration, HallDto hallDto){
        Date requestDateTime = request.getDateTime();


        List<SessionDto> sessionDtos = findAll();

        for (SessionDto session : sessionDtos) {
            if (session.getHall().getId().equals(hallDto.getId())) {
                if (!isSessionOverlap(requestDateTime, filmDuration,
                        session.getDateTime(), filmService.parseDuration(session.getFilm().getDuration()))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSessionOverlap(Date requestSessionDate, Duration requestSessionDuration,
                                     Date existingSessionDate, Duration existingSessionDuration){
        Date requestSessionEnd = addDurationToStartDate(requestSessionDate, requestSessionDuration);
        Date existingSessionEnd = addDurationToStartDate(existingSessionDate, existingSessionDuration);
        return compareEndTimes(requestSessionEnd, existingSessionEnd) == 0;
    }

    private Date addDurationToStartDate(Date startDate, Duration duration) {
        return new Date(startDate.toInstant().plus(duration).toEpochMilli());
    }

    private int compareEndTimes(Date endTime1, Date endTime2) {
        return endTime1.compareTo(endTime2);
    }
}
