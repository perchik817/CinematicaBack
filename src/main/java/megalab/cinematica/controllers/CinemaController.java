package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.dto.CinemaDto;
import megalab.cinematica.models.entity.Cinema;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cinema")
@AllArgsConstructor
@Api(tags = "Cinema control")
public class CinemaController {
    private final CinemaService cinemaService;

    @ApiIgnore
    @ApiOperation(value = "only for proger")
    @GetMapping("/find/by/id/{id}")
    ResponseEntity<?> findById (@PathVariable Long id, Language lang){
        return ResponseEntity.ok(cinemaService.findById(id, lang));
    }

    @GetMapping("/find/all")
    List<CinemaDto> findAll() {
        return cinemaService.findAll();
    }

    @PostMapping("/save")
    CinemaDto save(@RequestBody CinemaDto cinema) {
        return cinemaService.save(cinema);
    }

    @PostMapping("/update")
    CinemaDto update(@RequestBody CinemaDto cinema) {
        return cinemaService.update(cinema);
    }

}
