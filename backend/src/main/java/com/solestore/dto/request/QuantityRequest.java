package com.solestore.dto.request;

import jakarta.validation.constraints.Min;

public record QuantityRequest(@Min(1) int quantity) {}