package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SeatsCreateRequest;
import megalab.cinematica.service.SeatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seats")
@AllArgsConstructor
@Api(tags = "Seats control")
public class SeatsController {
    private final SeatsService seatsService;
    @GetMapping("/view/hall/seats")
    ResponseEntity<?> getHallSeats(@RequestParam Long id, @RequestParam Language lan){
        return ResponseEntity.ok(seatsService.getHallSeats(id, lan));
    }
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody SeatsCreateRequest data, Language lang) {
        return ResponseEntity.ok(seatsService.create(data, lang));
    }

    @PostMapping("/delete")
    ResponseEntity<?> delete(@RequestParam Long hallId,
                             @RequestParam int selectedSeat,
                             @RequestParam Language language){
        return ResponseEntity.ok(seatsService.removeSeat(hallId, selectedSeat, language));
    }
}
