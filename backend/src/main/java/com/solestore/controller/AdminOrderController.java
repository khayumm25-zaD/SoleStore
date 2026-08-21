package com.solestore.controller;

import com.solestore.dto.request.OrderStatusRequest;
import com.solestore.dto.response.OrderResponse;
import com.solestore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/admin/orders")
public class AdminOrderController {
    private final OrderService service;
    public AdminOrderController(OrderService service) { this.service = service; }
    @GetMapping public List<OrderResponse> findAll() { return service.findAll(); }
    @PutMapping("/{id}/status") public OrderResponse updateStatus(@PathVariable Long id, @Valid @RequestBody OrderStatusRequest request) { return service.updateStatus(id, request.status()); }
}