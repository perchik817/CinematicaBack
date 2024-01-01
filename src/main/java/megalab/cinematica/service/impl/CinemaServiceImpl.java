package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.CinemaMapper;
import megalab.cinematica.microservices.FileServiceFeign;
import megalab.cinematica.microservices.jsons.FileResponse;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.dao.rep.CinemaRep;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.CinemaService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaRep, CinemaDto, CinemaMapper> implements CinemaService{

    protected CinemaServiceImpl(CinemaRep cinemaRep, CinemaMapper mapper, FileServiceFeign fileService) {
        super(cinemaRep, mapper);
        this.fileService = fileService;
    }

    private final FileServiceFeign fileService;

    @Override
    public Response create(CinemaCreateRequest request, MultipartFile logo, Language language) {
        try{
            CinemaDto cinemaDto = new CinemaDto();

            FileResponse fileResponse = fileService.upload(logo);

            cinemaDto.setLogo(fileResponse.getDownloadUri());
            cinemaDto.setName(request.getName());
            cinemaDto.setDefinition(request.getDefinition());
            cinemaDto.setAddress(request.getAddress());
            mapper.toEntity(cinemaDto, context);

            return Response.getSuccessResponse(cinemaDto, language);
        }catch (Exception e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }
}
