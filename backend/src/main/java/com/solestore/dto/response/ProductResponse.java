package com.solestore.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponse(Long id, String name, String description, String brand, BigDecimal price, String imageUrl, boolean active, CategoryResponse category, List<ProductVariantResponse> variants, LocalDateTime createdAt, LocalDateTime updatedAt) {}