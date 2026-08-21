package com.solestore.service;

import com.solestore.dto.request.UserUpdateRequest;
import com.solestore.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    UserResponse findByEmail(String email);
    UserResponse update(Long id, UserUpdateRequest request);
    UserResponse updateByEmail(String email, UserUpdateRequest request);
    void delete(Long id);
}