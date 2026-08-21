package com.solestore.service.impl;

import com.solestore.dto.response.OrderResponse;
import com.solestore.dto.request.OrderCreateRequest;
import com.solestore.entity.CartItem;
import com.solestore.entity.OrderItem;
import com.solestore.entity.PaymentStatus;
import com.solestore.exception.BadRequestException;
import com.solestore.entity.OrderStatus;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.OrderRepository;
import com.solestore.repository.UserRepository;
import com.solestore.repository.CartRepository;
import com.solestore.repository.ProductVariantRepository;
import com.solestore.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository; private final UserRepository users; private final CartRepository carts; private final ProductVariantRepository variants; private final ApiMapper mapper;
    public OrderServiceImpl(OrderRepository repository, UserRepository users, CartRepository carts, ProductVariantRepository variants, ApiMapper mapper) { this.repository = repository; this.users = users; this.carts = carts; this.variants = variants; this.mapper = mapper; }
    public OrderResponse create(String email, OrderCreateRequest request) { var user = users.findByEmailIgnoreCase(email).orElseThrow(() -> new ResourceNotFoundException("User not found")); var cart = carts.findByUserId(user.getId()).orElseThrow(() -> new BadRequestException("Cart is empty")); if (cart.getItems().isEmpty()) throw new BadRequestException("Cart is empty"); var order = new com.solestore.entity.Order(); order.setUser(user); order.setShippingAddress(request.shippingAddress().trim()); order.setPaymentStatus(request.paymentMethod().equalsIgnoreCase("COD") ? PaymentStatus.COD : PaymentStatus.PAID); order.setTotalAmount(java.math.BigDecimal.ZERO); for (CartItem cartItem : cart.getItems()) { var variant = variants.findByProductIdAndSize(cartItem.getProduct().getId(), cartItem.getSize()).orElseThrow(() -> new BadRequestException("A cart size is no longer available")); if (!cartItem.getProduct().isActive() || variant.getStockQuantity() < cartItem.getQuantity()) throw new BadRequestException("Insufficient stock for " + cartItem.getProduct().getName()); variant.setStockQuantity(variant.getStockQuantity() - cartItem.getQuantity()); var item = new OrderItem(); item.setOrder(order); item.setProduct(cartItem.getProduct()); item.setSize(cartItem.getSize()); item.setQuantity(cartItem.getQuantity()); item.setPrice(cartItem.getPrice()); order.getItems().add(item); order.setTotalAmount(order.getTotalAmount().add(cartItem.getPrice().multiply(java.math.BigDecimal.valueOf(cartItem.getQuantity())))); } var saved = repository.save(order); cart.getItems().clear(); carts.save(cart); return mapper.order(saved); }
    @Transactional(readOnly = true) public List<OrderResponse> findForUser(String email) { var userId = userId(email); return repository.findByUserIdOrderByCreatedAtDesc(userId).stream().map(mapper::order).toList(); }
    @Transactional(readOnly = true) public OrderResponse findForUserById(String email, Long orderId) { var value = get(orderId); if (!value.getUser().getEmail().equalsIgnoreCase(email)) throw new ResourceNotFoundException("Order not found: " + orderId); return mapper.order(value); }
    @Transactional(readOnly = true) public List<OrderResponse> findAll() { return repository.findAll().stream().map(mapper::order).toList(); }
    public OrderResponse updateStatus(Long orderId, OrderStatus status) { var value = get(orderId); value.setStatus(status); return mapper.order(value); }
    private com.solestore.entity.Order get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id)); }
    private Long userId(String email) { return users.findByEmailIgnoreCase(email).map(com.solestore.entity.User::getId).orElseThrow(() -> new ResourceNotFoundException("User not found")); }
}