package com.example.demo.mapper;

import com.example.demo.entity.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {

    int insert(Doctor doctor);

    int update(Doctor doctor);

    int deleteById(@Param("id") Long id);

    Doctor selectById(@Param("id") Long id);

    List<Doctor> selectList(@Param("name") String name, @Param("department") String department);
}

