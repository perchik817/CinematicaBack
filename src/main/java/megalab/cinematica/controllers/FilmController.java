package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.dto.FilmDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1/film")
@AllArgsConstructor
@Api(tags = "Film control")
public class FilmController {

    private final FilmService filmService;

    @ApiIgnore
    @ApiOperation(value = "only for proger")
    @GetMapping("/find/by/id/{id}")
    ResponseEntity<?> findById (@PathVariable Long id, Language lang){
        return ResponseEntity.ok(filmService.findById(id, lang));
    }

    @GetMapping("/find/all")
    List<FilmDto> findAll() {
        return filmService.findAll();
    }

    @PostMapping("/save")
    FilmDto save(@RequestBody FilmDto film) {
        return filmService.save(film);
    }

    @PostMapping("/update")
    FilmDto update(@RequestBody FilmDto film) {
        return filmService.update(film);
    }

}
