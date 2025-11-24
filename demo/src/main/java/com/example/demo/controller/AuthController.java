package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ApiResponse<Admin> login(@Valid @RequestBody LoginRequest request) {
        Admin admin = adminService.login(request.getUsername(), request.getPassword());
        admin.setPassword(null);
        return ApiResponse.success(admin);
    }
}

