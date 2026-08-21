package com.solestore.service.impl;

import com.solestore.dto.request.ProductRequest;
import com.solestore.dto.response.ProductResponse;
import com.solestore.entity.Category;
import com.solestore.entity.Product;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.CategoryRepository;
import com.solestore.repository.ProductRepository;
import com.solestore.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository; private final CategoryRepository categories; private final ApiMapper mapper;
    public ProductServiceImpl(ProductRepository repository, CategoryRepository categories, ApiMapper mapper) { this.repository = repository; this.categories = categories; this.mapper = mapper; }
    public ProductResponse create(ProductRequest request) { Product value = new Product(); apply(value, request); return mapper.product(repository.save(value)); }
    @Transactional(readOnly = true) public List<ProductResponse> findAll() { return repository.findAll().stream().map(mapper::product).toList(); }
    @Transactional(readOnly = true) public ProductResponse findById(Long id) { return mapper.product(get(id)); }
    public ProductResponse update(Long id, ProductRequest request) { Product value = get(id); apply(value, request); return mapper.product(value); }
    public void delete(Long id) { repository.delete(get(id)); }
    private Product get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id)); }
    private void apply(Product value, ProductRequest request) { Category category = categories.findById(request.categoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found: " + request.categoryId())); value.setName(request.name().trim()); value.setDescription(request.description()); value.setBrand(request.brand().trim()); value.setPrice(request.price()); value.setImageUrl(request.imageUrl()); value.setActive(request.active()); value.setCategory(category); }
}