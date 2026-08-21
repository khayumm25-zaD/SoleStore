package com.solestore.controller;

import com.solestore.dto.request.ProductVariantRequest;
import com.solestore.dto.response.ProductVariantResponse;
import com.solestore.service.ProductVariantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
public class ProductVariantController {
    private final ProductVariantService service;
    public ProductVariantController(ProductVariantService service) { this.service = service; }
    @PostMapping("/api/products/{productId}/variants") public ResponseEntity<ProductVariantResponse> create(@PathVariable Long productId, @Valid @RequestBody ProductVariantRequest request) { ProductVariantResponse response = service.create(productId, request); return ResponseEntity.created(URI.create("/api/variants/" + response.id())).body(response); }
    @GetMapping("/api/products/{productId}/variants") public List<ProductVariantResponse> findByProduct(@PathVariable Long productId) { return service.findByProduct(productId); }
    @PutMapping("/api/variants/{id}") public ProductVariantResponse update(@PathVariable Long id, @Valid @RequestBody ProductVariantRequest request) { return service.update(id, request); }
    @DeleteMapping("/api/variants/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}