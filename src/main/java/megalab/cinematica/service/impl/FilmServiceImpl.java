package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.FilmRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.FilmMapper;
import megalab.cinematica.microservices.FileServiceFeign;
import megalab.cinematica.microservices.jsons.FileResponse;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.entity.Film;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.models.responces.FilmsResponse;
import megalab.cinematica.models.responces.FilmCinemasResponse;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.FilmService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
public class FilmServiceImpl extends BaseServiceImpl<Film, FilmRep, FilmDto, FilmMapper> implements FilmService {

    protected FilmServiceImpl(FilmRep filmRep, FilmMapper mapper, FileServiceFeign fileService) {
        super(filmRep, mapper);
        this.fileService = fileService;
    }

    private final FileServiceFeign fileService;

    @Override
    public Response create(FilmCreateRequest request, Language language) {
        try{
            Duration duration = parseDuration(request.getDuration());
            FilmDto filmDto = new FilmDto();
            FileResponse fileResponse = new FileResponse();
            filmDto.setLogo(fileResponse.getDownloadUri());
            filmDto.setName(request.getName());
            filmDto.setDefinition(request.getDefinition());
            filmDto.setGenre(request.getGenre());
            filmDto.setAgeRestrictions(request.getAgeRestrictions());
            filmDto.setFormat(request.getFormat());
            filmDto.setDuration(formatDuration(duration, language));
            save(filmDto);

            return Response.getSuccessResponse(filmDto, language);
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }

    @Override
    public String formatDuration(Duration duration, Language language) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        if(hours >= 1)
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        else
            throw new NumException(ResourceBundle.periodMess("incorrectDuration", language));
    }


    @Override
    public Duration parseDuration(String durationString) {
        String[] parts = durationString.split(":");
        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        long seconds = Long.parseLong(parts[2]);

        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    @Override
    public List<FilmsResponse> getAllFilms(int limit, int offset) {
        Page<FilmsResponse> pageResult = repo.getAllFilms(PageRequest.of(offset, limit));
        return pageResult.getContent();
    }


    @Override
    public FilmCinemasResponse getAllCinemasByFilm(Long movieId, Date date) {
        return null;
    }
}
