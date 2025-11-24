package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.entity.Doctor;
import com.example.demo.mapper.DoctorMapper;
import com.example.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;

    @Override
    public void create(Doctor doctor) {
        doctorMapper.insert(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        if (doctor.getId() == null) {
            throw new BusinessException(400, "医生ID不能为空");
        }
        doctorMapper.update(doctor);
    }

    @Override
    public void delete(Long id) {
        doctorMapper.deleteById(id);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorMapper.selectById(id);
    }

    @Override
    public List<Doctor> list(String name, String department) {
        return doctorMapper.selectList(StringUtils.hasText(name) ? "%" + name + "%" : null,
                StringUtils.hasText(department) ? "%" + department + "%" : null);
    }
}

