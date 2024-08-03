package com.poly.backend.controller;

import com.poly.backend.dto.OrderDTO;
import com.poly.backend.dto.OrderDetailDTO;
import com.poly.backend.exception.AppException;
import com.poly.backend.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (AppException e) {
            logger.error("Error fetching order by id {}: {}", id, e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDTO));
        } catch (AppException e) {
            logger.error("Error creating order: {}", e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
        try {
            return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
        } catch (AppException e) {
            logger.error("Error updating order id {}: {}", id, e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (AppException e) {
            logger.error("Error deleting order id {}: {}", id, e.getMessage());
            return ResponseEntity.status(e.getStatus()).build();
        }
    }
}
