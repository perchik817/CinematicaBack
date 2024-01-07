package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/film")
@AllArgsConstructor
@Api(tags = "Film control")
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody FilmCreateRequest data, Language language){
        return ResponseEntity.ok(filmService.create(data, language));
    }

    @GetMapping("/get/all/cinemas")
    ResponseEntity<?> getAllCinemas(@RequestParam int limit, int offset){
        return ResponseEntity.ok(filmService.getAllCinemas(limit, offset));
    }


    @GetMapping("/get/films/on/date")
    ResponseEntity<?> getAllFilms(@RequestParam Long movieId,
                                  @RequestParam Date date){
        return ResponseEntity.ok(filmService.getAllFilms(movieId, date));
    }

}
