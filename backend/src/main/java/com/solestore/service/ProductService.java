package com.solestore.service;

import com.solestore.dto.request.ProductRequest;
import com.solestore.dto.response.ProductResponse;
import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}