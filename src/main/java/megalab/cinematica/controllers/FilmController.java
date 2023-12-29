package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.service.FilmService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/film")
@AllArgsConstructor
@Api(tags = "Film control")
public class FilmController {

    private final FilmService filmService;


}
