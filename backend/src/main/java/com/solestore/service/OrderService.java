package com.solestore.service;

import com.solestore.dto.response.OrderResponse;
import com.solestore.dto.request.OrderCreateRequest;
import com.solestore.entity.OrderStatus;
import java.util.List;

public interface OrderService { OrderResponse create(String email, OrderCreateRequest request); List<OrderResponse> findForUser(String email); OrderResponse findForUserById(String email, Long orderId); List<OrderResponse> findAll(); OrderResponse updateStatus(Long orderId, OrderStatus status); }