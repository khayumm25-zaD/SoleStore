package com.solestore.controller;

import com.solestore.dto.response.DashboardResponse;
import com.solestore.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {
    private final UserRepository users; private final ProductRepository products; private final CategoryRepository categories; private final OrderRepository orders; private final ProductVariantRepository variants;
    public AdminDashboardController(UserRepository users, ProductRepository products, CategoryRepository categories, OrderRepository orders, ProductVariantRepository variants) { this.users = users; this.products = products; this.categories = categories; this.orders = orders; this.variants = variants; }
    @GetMapping public DashboardResponse get() { return new DashboardResponse(users.count(), products.count(), categories.count(), orders.count(), orders.totalRevenue(), variants.countByStockQuantityLessThanEqual(5)); }
}