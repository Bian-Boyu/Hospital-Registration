package com.example.demo.mapper;

import com.example.demo.entity.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientMapper {

    int insert(Patient patient);

    int update(Patient patient);

    int deleteById(@Param("id") Long id);

    Patient selectById(@Param("id") Long id);

    List<Patient> selectList(@Param("name") String name, @Param("phone") String phone);
}

