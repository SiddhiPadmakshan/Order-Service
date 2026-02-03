package com.siddhi.order_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.siddhi.order_service.dto.CreateOrderRequestDTO;
import com.siddhi.order_service.dto.OrderResponseDTO;
import com.siddhi.order_service.model.Order;
import com.siddhi.order_service.model.OrderStatus;
import com.siddhi.order_service.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDTO createOrder(@Valid @RequestBody CreateOrderRequestDTO request) {
        return service.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO  getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteOrder(id);
    }

    @GetMapping
    public Page<OrderResponseDTO> getAllOrders(
        @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return service.getAllOrders(pageable);
    }
}
