package com.solestore.service;

import com.solestore.dto.request.LoginRequest;
import com.solestore.dto.request.RegisterRequest;
import com.solestore.dto.response.AuthResponse;

public interface AuthService { AuthResponse register(RegisterRequest request); AuthResponse login(LoginRequest request); }