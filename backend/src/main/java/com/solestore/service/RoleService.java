package com.solestore.service;

import com.solestore.dto.response.RoleResponse;
import java.util.List;

public interface RoleService { List<RoleResponse> findAll(); RoleResponse findById(Long id); }