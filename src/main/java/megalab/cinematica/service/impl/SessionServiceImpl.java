package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SessionRep;
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
import org.springframework.stereotype.Service;

import java.time.Duration;

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
        FilmDto filmDto = filmService.findById(request.getFilmDto().getId(), language);
        HallDto hallDto = hallService.findById(request.getFilmDto().getId(), language);
        return null;
    }
}
