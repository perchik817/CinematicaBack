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
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody SeatsCreateRequest data, Language lang) {
        return ResponseEntity.ok(seatsService.create(data, lang));
    }
}
