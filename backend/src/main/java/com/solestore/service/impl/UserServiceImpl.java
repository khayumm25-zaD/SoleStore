package com.solestore.service.impl;

import com.solestore.dto.request.UserUpdateRequest;
import com.solestore.dto.response.UserResponse;
import com.solestore.entity.Role;
import com.solestore.entity.User;
import com.solestore.exception.DuplicateResourceException;
import com.solestore.exception.ResourceNotFoundException;
import com.solestore.mapper.ApiMapper;
import com.solestore.repository.RoleRepository;
import com.solestore.repository.UserRepository;
import com.solestore.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository; private final RoleRepository roles; private final ApiMapper mapper;
    public UserServiceImpl(UserRepository repository, RoleRepository roles, ApiMapper mapper) { this.repository = repository; this.roles = roles; this.mapper = mapper; }
    @Transactional(readOnly = true) public List<UserResponse> findAll() { return repository.findAll().stream().map(mapper::user).toList(); }
    @Transactional(readOnly = true) public UserResponse findById(Long id) { return mapper.user(get(id)); }
    @Transactional(readOnly = true) public UserResponse findByEmail(String email) { return mapper.user(repository.findByEmailIgnoreCase(email).orElseThrow(() -> new ResourceNotFoundException("User not found"))); }
    public UserResponse update(Long id, UserUpdateRequest request) { User value = get(id); if (!value.getEmail().equalsIgnoreCase(request.email()) && repository.existsByEmailIgnoreCase(request.email())) throw new DuplicateResourceException("Email already exists"); value.setName(request.name().trim()); value.setEmail(request.email().trim().toLowerCase()); value.setMobile(request.mobile()); if (request.roleId() != null) { Role role = roles.findById(request.roleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.roleId())); value.setRole(role); } return mapper.user(value); }
    public UserResponse updateByEmail(String email, UserUpdateRequest request) { return update(repository.findByEmailIgnoreCase(email).orElseThrow(() -> new ResourceNotFoundException("User not found")).getId(), request); }
    public void delete(Long id) { repository.delete(get(id)); }
    private User get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id)); }
}