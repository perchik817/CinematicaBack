package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.dao.rep.HallRep;
import megalab.cinematica.mappers.HallMapper;
import megalab.cinematica.models.dto.HallDto;
import megalab.cinematica.models.entity.Hall;
import megalab.cinematica.models.requests.HallCreateRequest;
import megalab.cinematica.service.HallService;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl extends BaseServiceImpl<Hall, HallRep, HallDto, HallMapper> implements HallService {
    protected HallServiceImpl(HallRep repo, HallMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public HallDto create(HallCreateRequest request) {
        return null;
    }
}
