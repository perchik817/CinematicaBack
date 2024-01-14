package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.models.entity.Hall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRep extends BaseRep<Hall> {
    boolean existsByName(String name);
    @Query("select c from Cinema c where c.id = :cinemaId")
    Cinema findByIdCinema(@Param("cinemaId") Long cinemaId);
}
