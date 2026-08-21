package com.solestore.dto.response;

import com.solestore.entity.OrderStatus;
import com.solestore.entity.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(Long id, Long userId, BigDecimal totalAmount, OrderStatus status, String shippingAddress, PaymentStatus paymentStatus, List<OrderItemResponse> items, LocalDateTime createdAt, LocalDateTime updatedAt) {}