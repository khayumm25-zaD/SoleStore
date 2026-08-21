package com.solestore.dto.request;

import com.solestore.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusRequest(@NotNull OrderStatus status) {}