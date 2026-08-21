package com.solestore.controller;

import com.solestore.dto.request.CategoryRequest;
import com.solestore.dto.response.CategoryResponse;
import com.solestore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController @RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService service;
	public CategoryController(CategoryService service) { this.service = service; }
	@PostMapping public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) { CategoryResponse response = service.create(request); return ResponseEntity.created(URI.create("/api/categories/" + response.id())).body(response); }
	@GetMapping public List<CategoryResponse> findAll() { return service.findAll(); }
	@GetMapping("/{id}") public CategoryResponse findById(@PathVariable Long id) { return service.findById(id); }
	@PutMapping("/{id}") public CategoryResponse update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) { return service.update(id, request); }
	@DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
