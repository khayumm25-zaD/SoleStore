package com.solestore.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(Long id, Long productId, String productName, String size, int quantity, BigDecimal price, BigDecimal subtotal) {}