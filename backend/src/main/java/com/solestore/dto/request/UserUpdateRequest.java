package com.solestore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(@NotBlank @Size(max = 100) String name, @NotBlank @Email @Size(max = 150) String email, @Size(max = 30) String mobile, Long roleId) {}