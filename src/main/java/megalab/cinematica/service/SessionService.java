package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.requests.SessionCreateRequest;

public interface SessionService extends BaseService<SessionDto> {
    SessionDto create(SessionCreateRequest request);
}
