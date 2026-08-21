package com.solestore.dto.response;

import java.time.LocalDateTime;

public record UserResponse(Long id, String name, String email, String mobile, Long roleId, String roleName, LocalDateTime createdAt, LocalDateTime updatedAt) {}