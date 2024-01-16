package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.FilmRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.CinemaMapper;
import megalab.cinematica.mappers.FilmMapper;
import megalab.cinematica.mappers.HallMapper;
import megalab.cinematica.mappers.SessionMapper;
import megalab.cinematica.microservices.FileServiceFeign;
import megalab.cinematica.microservices.jsons.FileResponse;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.dto.SessionDto;
import megalab.cinematica.models.entity.Film;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.models.responces.*;
import megalab.cinematica.service.FilmService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl extends BaseServiceImpl<Film, FilmRep, FilmDto, FilmMapper> implements FilmService {
    protected FilmServiceImpl(FilmRep filmRep, FilmMapper mapper, FileServiceFeign fileService,
                              SessionMapper sessionMapper, HallMapper hallMapper, CinemaMapper cinemaMapper) {
        super(filmRep, mapper);
        this.fileService = fileService;
        this.sessionMapper = sessionMapper;
        this.hallMapper = hallMapper;
        this.cinemaMapper = cinemaMapper;
    }

    private final FileServiceFeign fileService;

    private final SessionMapper sessionMapper;
    private final HallMapper hallMapper;
    private final CinemaMapper cinemaMapper;

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


    public List<SessionDto> findByHallAndDate(Long hallId, LocalDate date, Long movieId) {
        return sessionMapper.toDtos(repo.findByHallAndDate(hallId, date, movieId), context);
    }

    @Override
    public FilmSessionsResponse getAllSessionsByFilm(Long movieId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(LocalDate.parse(date, formatter));
        List<CinemaDto> cinemaDto = cinemaMapper.toDtos(repo.findByFilmAndDate(movieId,
                LocalDate.parse(date, formatter)), context);
        FilmSessionsResponse filmSessionsResponse = new FilmSessionsResponse();
        filmSessionsResponse.setCinemas(new ArrayList<>());
        for(CinemaDto cinemaDto1: cinemaDto){
            List<HallDto> hallDto = hallMapper.toDtos(repo.getHallByParameters(cinemaDto1.getId(),
                    LocalDate.parse(date, formatter), movieId), context);
            CinemaDetailsResponse cinemaDetails = new CinemaDetailsResponse();
            cinemaDetails.setRooms(new ArrayList<>());
            cinemaDetails.setName(cinemaDto1.getName());
            for(HallDto hallDto1: hallDto){
                HallDetailsResponse hallDetails = new HallDetailsResponse();
                hallDetails.setRoomMovieId(new ArrayList<>());
                List<SessionDto> sessionDtos = findByHallAndDate(hallDto1.getId(),
                        LocalDate.parse(date, formatter), movieId);

                hallDetails.setName(hallDto1.getName());
                for(SessionDto sessionDto: sessionDtos){
                    SessionDetailsResponse sessionDetails = new SessionDetailsResponse();
                    sessionDetails.setId(sessionDto.getId());
                    sessionDetails.setChildPrice(sessionDto.getPrice().getType().equals("CHILDREN")
                            ? null : sessionDto.getPrice().getPrice());
                    sessionDetails.setStandardPrice(sessionDto.getPrice().getType().equals("STANDARD")
                            ? sessionDto.getPrice().getPrice() : null);
                    sessionDetails.setStudentPrice(sessionDto.getPrice().getType().equals("STUDENT")
                            ? sessionDto.getPrice().getPrice() : null);
                    sessionDetails.setStartTime(sessionDto.getDateTime());
                    hallDetails.getRoomMovieId().add(sessionDetails);
                }
                cinemaDetails.getRooms().add(hallDetails);
            }
            filmSessionsResponse.getCinemas().add(cinemaDetails);
        }
        return filmSessionsResponse;
    }
}
