package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.entity.Registration;
import com.example.demo.mapper.RegistrationMapper;
import com.example.demo.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationMapper registrationMapper;

    @Override
    public void create(Registration registration) {
        blockConflict(registration.getDoctorId(), registration.getAppointmentTime(), null);
        registration.setCreatedAt(LocalDateTime.now());
        registrationMapper.insert(registration);
    }

    @Override
    public void update(Registration registration) {
        if (registration.getId() == null) {
            throw new BusinessException(400, "挂号ID不能为空");
        }
        blockConflict(registration.getDoctorId(), registration.getAppointmentTime(), registration.getId());
        registrationMapper.update(registration);
    }

    @Override
    public void delete(Long id) {
        registrationMapper.deleteById(id);
    }

    @Override
    public Registration findById(Long id) {
        return registrationMapper.selectById(id);
    }

    @Override
    public List<Registration> list(String patientName, String doctorName, String status) {
        return registrationMapper.selectList(StringUtils.hasText(patientName) ? "%" + patientName + "%" : null,
                StringUtils.hasText(doctorName) ? "%" + doctorName + "%" : null,
                StringUtils.hasText(status) ? status : null);
    }

    private void blockConflict(Long doctorId, LocalDateTime appointmentTime, Long excludeId) {
        if (doctorId == null || appointmentTime == null) {
            throw new BusinessException(400, "请选择医生与预约时间");
        }
        int count = registrationMapper.countConflict(doctorId, appointmentTime, excludeId);
        if (count > 0) {
            throw new BusinessException(409, "当前时间段已被预约");
        }
    }
}

