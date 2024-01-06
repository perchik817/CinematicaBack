package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.models.requests.OrderCreateRequest;
import megalab.cinematica.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
@Api(tags = "Order control")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody OrderCreateRequest data, Language language){
        return ResponseEntity.ok(orderService.create(data, language));
    }
}
