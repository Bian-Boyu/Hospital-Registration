package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.entity.Patient;
import com.example.demo.mapper.PatientMapper;
import com.example.demo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;

    @Override
    public void create(Patient patient) {
        patientMapper.insert(patient);
    }

    @Override
    public void update(Patient patient) {
        if (patient.getId() == null) {
            throw new BusinessException(400, "患者ID不能为空");
        }
        patientMapper.update(patient);
    }

    @Override
    public void delete(Long id) {
        patientMapper.deleteById(id);
    }

    @Override
    public Patient findById(Long id) {
        return patientMapper.selectById(id);
    }

    @Override
    public List<Patient> list(String name, String phone) {
        return patientMapper.selectList(StringUtils.hasText(name) ? "%" + name + "%" : null,
                StringUtils.hasText(phone) ? "%" + phone + "%" : null);
    }
}

