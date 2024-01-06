package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.SessionCreateRequest;
import megalab.cinematica.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/session")
@AllArgsConstructor
@Api(tags = "Session control")
public class SessionController {
    private final SessionService sessionService;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody SessionCreateRequest data, Language language){
        return ResponseEntity.ok(sessionService.create(data, language));
    }

}
