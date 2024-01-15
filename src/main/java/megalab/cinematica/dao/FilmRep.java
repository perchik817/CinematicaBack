package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Film;
import megalab.cinematica.models.responces.FilmCinemasResponse;
import megalab.cinematica.models.responces.FilmsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRep extends BaseRep<Film> {
    @Query("select f.id as id, f.logo as logo, f.ageRestrictions as ageRestrictions, f.name as name from Film f " +
            "where f.active = true")
    Page<FilmsResponse> getAllFilms(Pageable pageable);

    @Query("select s.id as id, s.price.price, s.dateTime as dateTime from Session s " +
            "where s.active = true")
    List<FilmCinemasResponse> getAllSessionsByFilm();
}
