package com.solestore.mapper;

import com.solestore.dto.response.*;
import com.solestore.entity.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ApiMapper {
    public CategoryResponse category(Category value) { return new CategoryResponse(value.getId(), value.getName(), value.getDescription()); }
    public ProductVariantResponse variant(ProductVariant value) { return new ProductVariantResponse(value.getId(), value.getSize(), value.getStockQuantity()); }
    public ProductResponse product(Product value) {
        return new ProductResponse(value.getId(), value.getName(), value.getDescription(), value.getBrand(), value.getPrice(), value.getImageUrl(), value.isActive(), category(value.getCategory()), value.getVariants().stream().map(this::variant).toList(), value.getCreatedAt(), value.getUpdatedAt());
    }
    public UserResponse user(User value) { return new UserResponse(value.getId(), value.getName(), value.getEmail(), value.getMobile(), value.getRole().getId(), value.getRole().getName(), value.getCreatedAt(), value.getUpdatedAt()); }
    public CartItemResponse cartItem(CartItem value) { BigDecimal subtotal = value.getPrice().multiply(BigDecimal.valueOf(value.getQuantity())); return new CartItemResponse(value.getId(), value.getProduct().getId(), value.getProduct().getName(), value.getProduct().getImageUrl(), value.getSize(), value.getQuantity(), value.getPrice(), subtotal); }
    public CartResponse cart(Cart value) { List<CartItemResponse> items = value.getItems().stream().map(this::cartItem).toList(); return new CartResponse(value.getId(), value.getUser().getId(), items, items.stream().map(CartItemResponse::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add)); }
    public OrderItemResponse orderItem(OrderItem value) { BigDecimal subtotal = value.getPrice().multiply(BigDecimal.valueOf(value.getQuantity())); return new OrderItemResponse(value.getId(), value.getProduct().getId(), value.getProduct().getName(), value.getSize(), value.getQuantity(), value.getPrice(), subtotal); }
    public OrderResponse order(Order value) { return new OrderResponse(value.getId(), value.getUser().getId(), value.getTotalAmount(), value.getStatus(), value.getShippingAddress(), value.getPaymentStatus(), value.getItems().stream().map(this::orderItem).toList(), value.getCreatedAt(), value.getUpdatedAt()); }
    public RoleResponse role(Role value) { return new RoleResponse(value.getId(), value.getName()); }
}