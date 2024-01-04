package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.FilmRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.FilmMapper;
import megalab.cinematica.microservices.FileServiceFeign;
import megalab.cinematica.microservices.jsons.FileResponse;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.entity.Film;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.FilmService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

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
            if(request.getDuration().isNegative() || request.getDuration().isZero()){
                FilmDto filmDto = new FilmDto();
                FileResponse fileResponse = fileService.upload(request.getLogo());
                filmDto.setLogo(fileResponse.getDownloadUri());
                filmDto.setName(request.getName());
                filmDto.setDefinition(request.getDefinition());
                filmDto.setGenre(request.getGenre());
                filmDto.setFormat(request.getFormat());
                filmDto.setDuration(request.getDuration());
                mapper.toEntity(filmDto, context);
                save(filmDto);

                return Response.getSuccessResponse(filmDto, language);
            } else {
                throw new NumException(ResourceBundle.periodMess("incorrectDuration", language));
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }
}
