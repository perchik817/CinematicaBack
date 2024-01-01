package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.responces.Response;
import org.springframework.web.multipart.MultipartFile;

public interface CinemaService extends BaseService<CinemaDto> {
    Response create(CinemaCreateRequest request, MultipartFile logo, Language language);
}
