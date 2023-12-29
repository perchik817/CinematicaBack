package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.service.CinemaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cinema")
@AllArgsConstructor
@Api(tags = "Cinema control")
public class CinemaController {
    private final CinemaService cinemaService;

}
