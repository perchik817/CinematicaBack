package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.PriceRep;
import megalab.cinematica.mappers.PriceMapper;
import megalab.cinematica.models.dto.PriceDto;
import megalab.cinematica.models.entity.Price;
import megalab.cinematica.models.requests.PriceCreateRequest;
import megalab.cinematica.service.PriceService;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl extends BaseServiceImpl<Price, PriceRep, PriceDto, PriceMapper> implements PriceService {
    protected PriceServiceImpl(PriceRep repo, PriceMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public PriceDto create(PriceCreateRequest request) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(request.getPrice());
        priceDto.setType(request.getType());
        mapper.toEntity(priceDto, context);
        return priceDto;
    }
}
