package com.solestore.service.impl;

import com.solestore.dto.request.LoginRequest;
import com.solestore.dto.request.RegisterRequest;
import com.solestore.dto.response.AuthResponse;
import com.solestore.entity.Role;
import com.solestore.entity.User;
import com.solestore.exception.DuplicateResourceException;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.repository.RoleRepository;
import com.solestore.repository.UserRepository;
import com.solestore.security.JwtService;
import com.solestore.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class AuthServiceImpl implements AuthService {
    private static final String CUSTOMER_ROLE = "ROLE_CUSTOMER";
    private final UserRepository users; private final RoleRepository roles; private final PasswordEncoder encoder; private final AuthenticationManager authenticationManager; private final JwtService jwt;
    public AuthServiceImpl(UserRepository users, RoleRepository roles, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtService jwt) { this.users = users; this.roles = roles; this.encoder = encoder; this.authenticationManager = authenticationManager; this.jwt = jwt; }
    public AuthResponse register(RegisterRequest request) {
        String email = request.email().trim().toLowerCase(); if (users.existsByEmailIgnoreCase(email)) throw new DuplicateResourceException("Email already exists");
        Role role = roles.findByName(CUSTOMER_ROLE).orElseGet(() -> { Role created = new Role(); created.setName(CUSTOMER_ROLE); return roles.save(created); });
        User user = new User(); user.setName(request.name().trim()); user.setEmail(email); user.setPassword(encoder.encode(request.password())); user.setMobile(request.mobile()); user.setRole(role); user = users.save(user); return response(user);
    }
    public AuthResponse login(LoginRequest request) { authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email().trim().toLowerCase(), request.password())); User user = users.findByEmailIgnoreCase(request.email().trim()).orElseThrow(() -> new ResourceNotFoundException("User not found")); return response(user); }
    private AuthResponse response(User user) { UserDetails details = org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole().getName().replace("ROLE_", "")).build(); return new AuthResponse(jwt.generateToken(details), "Bearer", user.getId(), user.getName(), user.getEmail(), user.getRole().getName()); }
}