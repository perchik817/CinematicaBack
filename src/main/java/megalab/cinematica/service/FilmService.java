package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.models.responces.Response;

public interface FilmService extends BaseService<FilmDto> {
    Response create(FilmCreateRequest request, Language language);
}
