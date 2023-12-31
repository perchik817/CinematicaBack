package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.CinemaCreateRequest;
import megalab.cinematica.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cinema")
@AllArgsConstructor
@Api(tags = "Cinema control")
public class CinemaController {
    private final CinemaService cinemaService;
    @PostMapping("/create")
    ResponseEntity<?> create(@ModelAttribute CinemaCreateRequest data, Language language){
        return ResponseEntity.ok(cinemaService.create(data, data.getLogo(), language));
    }
}
