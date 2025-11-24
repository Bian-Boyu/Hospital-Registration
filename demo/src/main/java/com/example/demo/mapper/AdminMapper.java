package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin selectByUsername(@Param("username") String username);
}

