package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.PriceCreateRequest;
import megalab.cinematica.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/price")
@AllArgsConstructor
@Api(tags = "Price control")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/create/price")
    ResponseEntity<?> create(@RequestParam PriceCreateRequest data, Language language){
        return ResponseEntity.ok(priceService.create(data, language));
    }
}
