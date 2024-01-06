package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.enums.Ticket;
import megalab.cinematica.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/price")
@AllArgsConstructor
@Api(tags = "Price control")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam double price, @RequestParam Ticket type, Language language){
        return ResponseEntity.ok(priceService.create(price, type, language));
    }
}
