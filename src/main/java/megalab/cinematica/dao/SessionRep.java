package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRep extends BaseRep<Session> {
    @Query("""
            select distinct s from Session s
            join Hall h on h.id = s.hall.id
            join Film f on f.id = s.film.id
            where h.id = :hallId and s.dateTime = :date and f.id = :movieId
""")
    List<Session> findByHallAndDate(@Param("hallId") Long hallId,
                                    @Param("date") LocalDate date,
                                    @Param("filmId") Long movieId);

}
