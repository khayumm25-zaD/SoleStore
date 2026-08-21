package com.solestore.service.impl;

import com.solestore.dto.response.RoleResponse;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.RoleRepository;
import com.solestore.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository; private final ApiMapper mapper;
    public RoleServiceImpl(RoleRepository repository, ApiMapper mapper) { this.repository = repository; this.mapper = mapper; }
    public List<RoleResponse> findAll() { return repository.findAll().stream().map(mapper::role).toList(); }
    public RoleResponse findById(Long id) { return mapper.role(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found: " + id))); }
}