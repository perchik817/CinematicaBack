package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.FilmCreateRequest;
import megalab.cinematica.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/film")
@AllArgsConstructor
@Api(tags = "Film control")
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute FilmCreateRequest data, Language language){
        return ResponseEntity.ok(filmService.create(data, language));
    }
}
