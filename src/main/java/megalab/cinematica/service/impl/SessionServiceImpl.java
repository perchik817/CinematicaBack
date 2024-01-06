package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SessionRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.FilmMapper;
import megalab.cinematica.mappers.HallMapper;
import megalab.cinematica.mappers.PriceMapper;
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
    protected SessionServiceImpl(SessionRep repo, SessionMapper mapper, FilmService filmService,
                                 FilmMapper filmMapper, HallService hallService, HallMapper hallMapper,
                                 PriceService priceService, PriceMapper priceMapper) {
        super(repo, mapper);
        this.filmService = filmService;
        this.filmMapper = filmMapper;
        this.hallService = hallService;
        this.hallMapper = hallMapper;
        this.priceService = priceService;
        this.priceMapper = priceMapper;
    }

    private final FilmService filmService;
    private final FilmMapper filmMapper;
    private final HallService hallService;
    private final HallMapper hallMapper;
    private final PriceService priceService;
    private final PriceMapper priceMapper;

    @Override
    public Response create(SessionCreateRequest request, Language language) {
        try{
            if(repo.findById(request.getFilmId()) != null &&
                repo.findById(request.getHallId()) != null &&
                repo.findById(request.getPriceId()) != null) {
                HallDto hallDto = hallService.findById(request.getHallId(), language);
                FilmDto filmDto = filmService.findById(request.getFilmId(), language);
                PriceDto priceDto = priceService.findById(request.getPriceId(), language);
                SessionDto sessionDto = new SessionDto();

                Duration filmDuration = filmService.parseDuration(filmDto.getDuration());

                if (!areSessionsInSimilarHall(request, filmDuration, hallDto)) {
                    if (priceDto.getPrice() > 0) {
                        sessionDto.setHallDto(hallMapper.toDto(hallMapper.toEntity(hallDto, context), context));
                        sessionDto.setFilmDto(filmMapper.toDto(filmMapper.toEntity(filmDto, context), context));
                        sessionDto.setPriceDto(priceMapper.toDto(priceMapper.toEntity(priceDto, context), context));
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
            if (session.getHallDto().getId().equals(hallDto.getId())) {
                if (isSessionOverlap(requestDateTime, filmDuration,
                        session.getDateTime(), filmService.parseDuration(session.getFilmDto().getDuration()))) {
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
        return compareEndTimes(requestSessionEnd, existingSessionEnd) < 0;
    }

    private Date addDurationToStartDate(Date startDate, Duration duration) {
        return new Date(startDate.toInstant().plus(duration).toEpochMilli());
    }

    private int compareEndTimes(Date endTime1, Date endTime2) {
        return endTime1.compareTo(endTime2);
    }
}
