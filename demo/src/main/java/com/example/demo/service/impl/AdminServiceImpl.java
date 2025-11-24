package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        Admin admin = adminMapper.selectByUsername(username);
        if (admin == null || !admin.getPassword().equals(password)) {
            throw new BusinessException(401, "账号或密码错误");
        }
        return admin;
    }
}

