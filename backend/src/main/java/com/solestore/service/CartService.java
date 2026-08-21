package com.solestore.service;

import com.solestore.dto.request.CartItemRequest;
import com.solestore.dto.response.CartResponse;

public interface CartService { CartResponse get(String email); CartResponse add(String email, CartItemRequest request); CartResponse update(String email, Long itemId, int quantity); void remove(String email, Long itemId); void clear(String email); }