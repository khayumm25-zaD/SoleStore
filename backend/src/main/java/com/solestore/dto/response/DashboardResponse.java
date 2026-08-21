package com.solestore.dto.response;

import java.math.BigDecimal;

public record DashboardResponse(long totalUsers, long totalProducts, long totalCategories, long totalOrders, BigDecimal totalRevenue, long lowStockProducts) {}
