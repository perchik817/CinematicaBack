package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.models.entity.Film;
import megalab.cinematica.models.entity.Hall;
import megalab.cinematica.models.responces.FilmSessionsResponse;
import megalab.cinematica.models.responces.FilmsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRep extends BaseRep<Film> {
    @Query("select f.id as id, f.logo as logo, f.ageRestrictions as ageRestrictions, f.name as name from Film f " +
            "where f.active = true")
    Page<FilmsResponse> getAllFilms(Pageable pageable);

    @Query(value = """
                select distinct c.* from tb_cinema c
                join tb_hall h on h.id_cinema = c.id
                join tb_session s on s.id_hall = h.id
                join tb_films f on f.id = s.id_films
                where f.id = :movieId and s.date = :date
            """, nativeQuery = true)
    List<Cinema> findByFilmAndDate(@Param("filmId") Long movieId,
                                   @Param("date") LocalDate date);

    @Query("""
            select distinct h from Hall h
                join Cinema c on c.id = :cinemaId
                join Session s on s.dateTime = :date
                join Film f on f.id = s.film.id
            where s.hall.id = h.id and h.cinema.id = c.id and f.id = :movieId
            """)
    List<Hall> getHallByParameters(@Param("cinemaId") Long cinemaId,
                                   @Param("date") LocalDate date,
                                   @Param("movieId") Long movieId);


}
