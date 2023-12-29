package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.SeatsRep;
import megalab.cinematica.mappers.SeatsMapper;
import megalab.cinematica.models.dto.SeatsDto;
import megalab.cinematica.models.entity.Seats;
import megalab.cinematica.models.requests.SeatsCreateRequest;
import megalab.cinematica.service.SeatsService;
import org.springframework.stereotype.Service;

@Service
public class SeatsServiceImpl extends BaseServiceImpl<Seats, SeatsRep, SeatsDto, SeatsMapper> implements SeatsService {
    protected SeatsServiceImpl(SeatsRep repo, SeatsMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public SeatsDto create(SeatsCreateRequest request) {
        return null;
    }
}
