package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.HallCreateRequest;
import megalab.cinematica.service.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hall")
@AllArgsConstructor
@Api(tags = "Hall control")
public class HallController {
    private final HallService hallService;
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam HallCreateRequest request, Language lang){
        return ResponseEntity.ok(hallService.create(request, lang));

    }
}
