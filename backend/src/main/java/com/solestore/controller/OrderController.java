package com.solestore.controller;

import com.solestore.dto.response.OrderResponse;
import com.solestore.dto.request.OrderCreateRequest;
import com.solestore.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController @RequestMapping("/api/orders")
public class OrderController {
	private final OrderService service;
	public OrderController(OrderService service) { this.service = service; }
	@PostMapping public ResponseEntity<OrderResponse> create(Authentication authentication, @Valid @RequestBody OrderCreateRequest request) { return ResponseEntity.status(201).body(service.create(authentication.getName(), request)); }
	@GetMapping public List<OrderResponse> findForUser(Authentication authentication) { return service.findForUser(authentication.getName()); }
	@GetMapping("/{id}") public OrderResponse findById(Authentication authentication, @PathVariable Long id) { return service.findForUserById(authentication.getName(), id); }
}
