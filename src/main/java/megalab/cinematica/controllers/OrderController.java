package megalab.cinematica.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import megalab.cinematica.models.dto.OrderDto;
import megalab.cinematica.models.enums.Language;
import megalab.cinematica.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
@Api(tags = "Order control")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/find/by/id/{id}")
    ResponseEntity<?> findById (@PathVariable Long id, Language lang){
        return ResponseEntity.ok(orderService.findById(id, lang));
    }

    @GetMapping("/find/all")
    List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/save")
    OrderDto save(@RequestBody OrderDto order) {
        return orderService.save(order);
    }

    @PostMapping("/update")
    OrderDto update(@RequestBody OrderDto order) {
        return orderService.update(order);
    }
}
