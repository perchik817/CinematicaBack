package megalab.cinematica.controllers;

import lombok.AllArgsConstructor;
import megalab.cinematica.models.Order;
import megalab.cinematica.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/find/by/id/{id}")
    ResponseEntity<?> findById (@PathVariable Long id){
        try{
            return ResponseEntity.ok(orderService.findById(id));
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/find/all")
    List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/save")
    Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PostMapping("/update")
    Order update(@RequestBody Order order) {
        return orderService.update(order);
    }
}
