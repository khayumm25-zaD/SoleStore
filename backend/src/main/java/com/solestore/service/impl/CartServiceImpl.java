package com.solestore.service.impl;

import com.solestore.dto.request.CartItemRequest;
import com.solestore.dto.response.CartResponse;
import com.solestore.entity.*;
import com.solestore.exception.BadRequestException;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.*;
import com.solestore.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository carts; private final CartItemRepository items; private final UserRepository users; private final ProductRepository products; private final ProductVariantRepository variants; private final ApiMapper mapper;
    public CartServiceImpl(CartRepository carts, CartItemRepository items, UserRepository users, ProductRepository products, ProductVariantRepository variants, ApiMapper mapper) { this.carts = carts; this.items = items; this.users = users; this.products = products; this.variants = variants; this.mapper = mapper; }
    public CartResponse get(String email) { return mapper.cart(findCart(email)); }
    public CartResponse add(String email, CartItemRequest request) { Cart cart = findOrCreate(email); Product product = products.findById(request.productId()).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + request.productId())); if (!product.isActive()) throw new BadRequestException("Product is inactive"); ProductVariant variant = variants.findByProductIdAndSize(product.getId(), request.size()).orElseThrow(() -> new BadRequestException("Requested size is unavailable")); CartItem item = items.findByCartIdAndProductIdAndSize(cart.getId(), product.getId(), request.size()).orElseGet(() -> { CartItem created = new CartItem(); created.setCart(cart); created.setProduct(product); created.setSize(request.size()); created.setPrice(product.getPrice()); return created; }); int quantity = item.getQuantity() + request.quantity(); if (quantity > variant.getStockQuantity()) throw new BadRequestException("Requested quantity exceeds stock"); item.setQuantity(quantity); items.save(item); return mapper.cart(cart); }
    public CartResponse update(String email, Long itemId, int quantity) { if (quantity < 1) throw new BadRequestException("Quantity must be at least 1"); CartItem item = ownedItem(email, itemId); ProductVariant variant = variants.findByProductIdAndSize(item.getProduct().getId(), item.getSize()).orElseThrow(() -> new BadRequestException("Requested size is unavailable")); if (quantity > variant.getStockQuantity()) throw new BadRequestException("Requested quantity exceeds stock"); item.setQuantity(quantity); return mapper.cart(item.getCart()); }
    public void remove(String email, Long itemId) { items.delete(ownedItem(email, itemId)); }
    public void clear(String email) { Cart cart = findCart(email); items.deleteAll(cart.getItems()); cart.getItems().clear(); }
    private Cart findOrCreate(String email) { User user = users.findByEmailIgnoreCase(email).orElseThrow(() -> new ResourceNotFoundException("User not found")); return carts.findByUserId(user.getId()).orElseGet(() -> { Cart cart = new Cart(); cart.setUser(user); return carts.save(cart); }); }
    private Cart findCart(String email) { return findOrCreate(email); }
    private CartItem ownedItem(String email, Long itemId) { CartItem item = items.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Cart item not found: " + itemId)); if (!item.getCart().getUser().getEmail().equalsIgnoreCase(email)) throw new ResourceNotFoundException("Cart item not found: " + itemId); return item; }
}