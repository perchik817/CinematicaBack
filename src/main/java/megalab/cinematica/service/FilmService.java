package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.requests.FilmCreateRequest;

public interface FilmService extends BaseService<FilmDto> {
    FilmDto create(FilmCreateRequest request);
}
