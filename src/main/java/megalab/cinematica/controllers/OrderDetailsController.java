package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.OrderDetailsCreateRequest;
import megalab.cinematica.service.OrderDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order/details")
@Api(tags = "Order Details Control")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @PostMapping("/create")
    ResponseEntity<?> create (@RequestBody OrderDetailsCreateRequest data, Language language){
        return ResponseEntity.ok(orderDetailsService.create(data, language));
    }
}
