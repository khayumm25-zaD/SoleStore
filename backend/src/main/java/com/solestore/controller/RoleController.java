package com.solestore.controller;

import com.solestore.dto.response.RoleResponse;
import com.solestore.service.RoleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/roles")
public class RoleController {
    private final RoleService service;
    public RoleController(RoleService service) { this.service = service; }
    @GetMapping public List<RoleResponse> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public RoleResponse findById(@PathVariable Long id) { return service.findById(id); }
}