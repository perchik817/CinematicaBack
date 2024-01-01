package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SessionRep;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.SessionMapper;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.entity.Session;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SessionCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.FilmService;
import megalab.cinematica.service.HallService;
import megalab.cinematica.service.SessionService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
public class SessionServiceImpl extends BaseServiceImpl<Session, SessionRep, SessionDto, SessionMapper> implements SessionService {
    protected SessionServiceImpl(SessionRep repo, SessionMapper mapper, FilmService filmService, HallService hallService) {
        super(repo, mapper);
        this.filmService = filmService;
        this.hallService = hallService;
    }

    private final FilmService filmService;
    private final HallService hallService;

    @Override
    public Response create(SessionCreateRequest request, Language language) {
        try{
            FilmDto filmDto = filmService.findById(request.getFilmDto().getId(), language);
            HallDto hallDto = hallService.findById(request.getFilmDto().getId(), language);
            SessionDto sessionDto = new SessionDto();

            if(!areSessionsInSimilarHall(request, filmDto, hallDto)){
                sessionDto.setFilmDto(request.getFilmDto());
                sessionDto.setHallDto(request.getHallDto());
                sessionDto.setDateTime(request.getDateTime());
                sessionDto.setPrice(request.getPrice());
                sessionDto.setDiscount(request.getDiscount());

                mapper.toEntity(sessionDto, context);

                return Response.getSuccessResponse(sessionDto, language);
            }else {
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

    private boolean isSessionOverlap(Date newSessionStart, Duration newSessionDuration, Date existingSessionStart, Duration existingSessionDuration) {
        long newSessionEndMillis = newSessionStart.getTime() + newSessionDuration.toMillis();
        long existingSessionEndMillis = existingSessionStart.getTime() + existingSessionDuration.toMillis();

        return (newSessionStart.before(existingSessionStart) && newSessionEndMillis > existingSessionStart.getTime()) ||
                (newSessionStart.getTime() < existingSessionEndMillis && newSessionEndMillis > existingSessionEndMillis);
    }
}
