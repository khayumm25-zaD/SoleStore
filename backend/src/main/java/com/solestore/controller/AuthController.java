package com.solestore.controller;

import com.solestore.dto.request.LoginRequest;
import com.solestore.dto.request.RegisterRequest;
import com.solestore.dto.response.AuthResponse;
import com.solestore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth")
public class AuthController {
	private final AuthService service;
	public AuthController(AuthService service) { this.service = service; }
	@PostMapping("/register") public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) { return ResponseEntity.status(201).body(service.register(request)); }
	@PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest request) { return service.login(request); }
}
