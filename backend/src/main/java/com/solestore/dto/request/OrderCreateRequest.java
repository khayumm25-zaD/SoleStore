package com.solestore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrderCreateRequest(@NotBlank @Size(max = 1000) String shippingAddress, @NotBlank String paymentMethod) {}
