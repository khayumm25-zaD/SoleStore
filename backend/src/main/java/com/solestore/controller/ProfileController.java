package com.solestore.controller;

import com.solestore.dto.request.UserUpdateRequest;
import com.solestore.dto.response.UserResponse;
import com.solestore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/profile")
public class ProfileController {
    private final UserService service;
    public ProfileController(UserService service) { this.service = service; }
    @GetMapping public UserResponse get(Authentication authentication) { return service.findByEmail(authentication.getName()); }
    @PutMapping public UserResponse update(Authentication authentication, @Valid @RequestBody UserUpdateRequest request) { return service.updateByEmail(authentication.getName(), new UserUpdateRequest(request.name(), request.email(), request.mobile(), null)); }
}