package com.solestore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductVariantRequest(@NotBlank @Size(max = 20) String size, @Min(0) int stockQuantity) {}