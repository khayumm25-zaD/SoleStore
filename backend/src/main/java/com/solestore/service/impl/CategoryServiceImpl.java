package com.solestore.service.impl;

import com.solestore.dto.request.CategoryRequest;
import com.solestore.dto.response.CategoryResponse;
import com.solestore.entity.Category;
import com.solestore.exception.DuplicateResourceException;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.CategoryRepository;
import com.solestore.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository; private final ApiMapper mapper;
    public CategoryServiceImpl(CategoryRepository repository, ApiMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public CategoryResponse create(CategoryRequest request) { if (repository.existsByNameIgnoreCase(request.name())) throw new DuplicateResourceException("Category name already exists"); Category value = new Category(); apply(value, request); return mapper.category(repository.save(value)); }
    @Transactional(readOnly = true) public List<CategoryResponse> findAll() { return repository.findAll().stream().map(mapper::category).toList(); }
    @Transactional(readOnly = true) public CategoryResponse findById(Long id) { return mapper.category(get(id)); }
    public CategoryResponse update(Long id, CategoryRequest request) { Category value = get(id); if (!value.getName().equalsIgnoreCase(request.name()) && repository.existsByNameIgnoreCase(request.name())) throw new DuplicateResourceException("Category name already exists"); apply(value, request); return mapper.category(value); }
    public void delete(Long id) { repository.delete(get(id)); }
    private Category get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id)); }
    private void apply(Category value, CategoryRequest request) { value.setName(request.name().trim()); value.setDescription(request.description()); }
}