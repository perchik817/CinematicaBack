package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SessionRep;
import megalab.cinematica.mappers.SessionMapper;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.entity.Session;
import megalab.cinematica.models.requests.SessionCreateRequest;
import megalab.cinematica.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends BaseServiceImpl<Session, SessionRep, SessionDto, SessionMapper> implements SessionService {
    protected SessionServiceImpl(SessionRep repo, SessionMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public SessionDto create(SessionCreateRequest request) {
        return null;
    }
}
