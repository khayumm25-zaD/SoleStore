package com.solestore.controller;

import com.solestore.dto.request.UserUpdateRequest;
import com.solestore.dto.response.UserResponse;
import com.solestore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController @RequestMapping("/api/users")
public class UserController {
	private final UserService service;
	public UserController(UserService service) { this.service = service; }
	@PreAuthorize("hasRole('ADMIN')") @GetMapping public List<UserResponse> findAll() { return service.findAll(); }
	@PreAuthorize("hasRole('ADMIN')") @GetMapping("/{id}") public UserResponse findById(@PathVariable Long id) { return service.findById(id); }
	@PreAuthorize("hasRole('ADMIN')") @PutMapping("/{id}") public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) { return service.update(id, request); }
	@PreAuthorize("hasRole('ADMIN')") @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
