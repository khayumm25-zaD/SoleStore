package com.solestore.controller;

import com.solestore.dto.request.CartItemRequest;
import com.solestore.dto.request.QuantityRequest;
import com.solestore.dto.response.CartResponse;
import com.solestore.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController @RequestMapping("/api/cart")
public class CartController {
	private final CartService service;
	public CartController(CartService service) { this.service = service; }
	@GetMapping public CartResponse get(Authentication authentication) { return service.get(authentication.getName()); }
	@PostMapping("/items") public CartResponse add(Authentication authentication, @Valid @RequestBody CartItemRequest request) { return service.add(authentication.getName(), request); }
	@PutMapping("/items/{id}") public CartResponse update(Authentication authentication, @PathVariable Long id, @Valid @RequestBody QuantityRequest request) { return service.update(authentication.getName(), id, request.quantity()); }
	@DeleteMapping("/items/{id}") public ResponseEntity<Void> remove(Authentication authentication, @PathVariable Long id) { service.remove(authentication.getName(), id); return ResponseEntity.noContent().build(); }
	@DeleteMapping public ResponseEntity<Void> clear(Authentication authentication) { service.clear(authentication.getName()); return ResponseEntity.noContent().build(); }
}
