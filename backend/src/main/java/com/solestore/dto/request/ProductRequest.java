package com.solestore.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(max = 150) String name,
        @Size(max = 2000) String description,
        @NotBlank @Size(max = 100) String brand,
        @NotNull @DecimalMin("0.00") BigDecimal price,
        @Size(max = 500) String imageUrl,
        boolean active,
        @NotNull @Positive Long categoryId) {}