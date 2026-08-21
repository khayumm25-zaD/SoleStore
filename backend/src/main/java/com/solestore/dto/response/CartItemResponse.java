package com.solestore.dto.response;

import java.math.BigDecimal;

public record CartItemResponse(Long id, Long productId, String productName, String imageUrl, String size, int quantity, BigDecimal price, BigDecimal subtotal) {}