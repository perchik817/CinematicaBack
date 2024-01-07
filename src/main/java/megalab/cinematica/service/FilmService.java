package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.models.responces.FilmCinemaResponse;
import megalab.cinematica.models.responces.FilmResponse;
import megalab.cinematica.models.responces.Response;

import java.time.Duration;
import java.util.Date;

public interface FilmService extends BaseService<FilmDto> {
    Response create(FilmCreateRequest request, Language language);
    String formatDuration(Duration duration, Language language);
    Duration parseDuration(String durationString);

    FilmCinemaResponse getAllCinemas(int limit, int offset);

    FilmResponse getAllFilms(Long movieId, Date date);
}
