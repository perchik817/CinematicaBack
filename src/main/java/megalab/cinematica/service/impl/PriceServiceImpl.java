package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.PriceRep;
import megalab.cinematica.exceptions.NumException;
import megalab.cinematica.exceptions.UnsavedDataException;
import megalab.cinematica.mappers.PriceMapper;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.entity.Price;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.PriceCreateRequest;
import megalab.cinematica.models.responces.Response;
import megalab.cinematica.service.PriceService;
import megalab.cinematica.utils.ResourceBundle;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl extends BaseServiceImpl<Price, PriceRep, PriceDto, PriceMapper> implements PriceService {
    protected PriceServiceImpl(PriceRep repo, PriceMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public Response create(PriceCreateRequest request, Language language) {
        try{
            if(request.getPrice() > 0){
                PriceDto priceDto = new PriceDto();
                priceDto.setPrice(request.getPrice());
                priceDto.setType(request.getType());

                mapper.toEntity(priceDto, context);
                save(priceDto);

                return Response.getSuccessResponse(priceDto, language);
            }else {
                throw new NumException(ResourceBundle.periodMess("priceIsNegative", language));
            }
        }catch (UnsavedDataException e){
            throw new UnsavedDataException(ResourceBundle.periodMess("unsavedData", language));
        }
    }
}
