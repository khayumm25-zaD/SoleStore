package com.solestore.service.impl;

import com.solestore.dto.request.ProductVariantRequest;
import com.solestore.dto.response.ProductVariantResponse;
import com.solestore.entity.Product;
import com.solestore.entity.ProductVariant;
import com.solestore.exception.DuplicateResourceException;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.ProductRepository;
import com.solestore.repository.ProductVariantRepository;
import com.solestore.service.ProductVariantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository repository; private final ProductRepository products; private final ApiMapper mapper;
    public ProductVariantServiceImpl(ProductVariantRepository repository, ProductRepository products, ApiMapper mapper) { this.repository = repository; this.products = products; this.mapper = mapper; }
    public ProductVariantResponse create(Long productId, ProductVariantRequest request) { Product product = products.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId)); if (repository.findByProductIdAndSize(productId, request.size()).isPresent()) throw new DuplicateResourceException("Variant size already exists for product"); ProductVariant value = new ProductVariant(); value.setProduct(product); apply(value, request); return mapper.variant(repository.save(value)); }
    @Transactional(readOnly = true) public List<ProductVariantResponse> findByProduct(Long productId) { if (!products.existsById(productId)) throw new ResourceNotFoundException("Product not found: " + productId); return products.findById(productId).orElseThrow().getVariants().stream().map(mapper::variant).toList(); }
    public ProductVariantResponse update(Long id, ProductVariantRequest request) { ProductVariant value = get(id); if (!value.getSize().equalsIgnoreCase(request.size()) && repository.findByProductIdAndSize(value.getProduct().getId(), request.size()).isPresent()) throw new DuplicateResourceException("Variant size already exists for product"); apply(value, request); return mapper.variant(value); }
    public void delete(Long id) { repository.delete(get(id)); }
    private ProductVariant get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product variant not found: " + id)); }
    private void apply(ProductVariant value, ProductVariantRequest request) { value.setSize(request.size().trim()); value.setStockQuantity(request.stockQuantity()); }
}