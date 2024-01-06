package megalab.cinematica.dao.rep;

import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.models.entity.Hall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRep extends BaseRep<Hall> {
    @Query("select count (h) from Hall h where h.name = :hallName")
    int countHallsByName(@Param("hallName") String hallName);

    @Query("select c from Cinema c where c.id = :cinemaId")
    Cinema findByIdEnt(@Param("cinemaId") Long cinemaId);
}
