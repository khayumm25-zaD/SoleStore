package com.solestore.service;

import com.solestore.dto.request.ProductVariantRequest;
import com.solestore.dto.response.ProductVariantResponse;
import java.util.List;

public interface ProductVariantService {
    ProductVariantResponse create(Long productId, ProductVariantRequest request);
    List<ProductVariantResponse> findByProduct(Long productId);
    ProductVariantResponse update(Long id, ProductVariantRequest request);
    void delete(Long id);
}