package megalab.cinematica.controllers;

import lombok.AllArgsConstructor;
import megalab.cinematica.models.Cinema;
import megalab.cinematica.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cinema")
@AllArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @GetMapping("/find/by/id/{id}")
    ResponseEntity<?> findById (@PathVariable Long id){
        try{
            return ResponseEntity.ok(cinemaService.findById(id));
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/find/all")
    List<Cinema> findAll() {
        return cinemaService.findAll();
    }

    @PostMapping("/save")
    Cinema save(@RequestBody Cinema cinema) {
        return cinemaService.save(cinema);
    }

    @PostMapping("/update")
    Cinema update(@RequestBody Cinema cinema) {
        return cinemaService.update(cinema);
    }
}
