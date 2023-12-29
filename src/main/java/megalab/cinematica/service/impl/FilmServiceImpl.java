package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.FilmRep;
import megalab.cinematica.mappers.FilmMapper;
import megalab.cinematica.microservices.FileService;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.entity.Film;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.service.FilmService;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl extends BaseServiceImpl<Film, FilmRep, FilmDto, FilmMapper> implements FilmService {

    protected FilmServiceImpl(FilmRep filmRep, FilmMapper mapper, FileService fileService) {
        super(filmRep, mapper);
        this.fileService = fileService;
    }

    private final FileService fileService;

    @Override
    public FilmDto create(FilmCreateRequest request) {
        //TODO add logo
        FilmDto filmDto = new FilmDto();
        //filmDto.setLogo();
        filmDto.setName(request.getName());
        filmDto.setDefinition(request.getDefinition());
        filmDto.setGenre(request.getGenre());
        filmDto.setFormat(request.getFormat());
        mapper.toEntity(filmDto, context);

        return null;//TODO response
    }
}
