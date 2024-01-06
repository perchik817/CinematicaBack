package megalab.cinematica.dao.rep;

import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Hall;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRep extends BaseRep<Hall> {
    boolean existsByName(String name);
}
