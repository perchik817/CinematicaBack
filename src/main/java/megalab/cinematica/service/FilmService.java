package megalab.cinematica.service;

import megalab.cinematica.base.BaseService;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.models.responces.FilmsResponse;
import megalab.cinematica.models.responces.FilmSessionsResponse;
import megalab.cinematica.models.responces.Response;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public interface FilmService extends BaseService<FilmDto> {
    Response create(FilmCreateRequest request, Language language);
    String formatDuration(Duration duration, Language language);
    Duration parseDuration(String durationString);

    List<FilmsResponse> getAllFilms(int limit, int offset);

    FilmSessionsResponse getAllSessionsByFilm(Long movieId, String date);
}
