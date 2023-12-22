package megalab.cinematica.service.impl;

import megalab.cinematica.base.BaseServiceImpl;
import megalab.cinematica.models.Cinema;
import megalab.cinematica.dao.rep.CinemaRep;
import megalab.cinematica.service.CinemaService;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl extends BaseServiceImpl<Cinema, CinemaRep> implements CinemaService{
    public CinemaServiceImpl(CinemaRep cinemaRep) {
        super(cinemaRep);
    }
}
