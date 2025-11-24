package com.example.demo.mapper;

import com.example.demo.entity.Registration;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistrationMapper {

    int insert(Registration registration);

    int update(Registration registration);

    int deleteById(@Param("id") Long id);

    Registration selectById(@Param("id") Long id);

    List<Registration> selectList(@Param("patientName") String patientName,
                                  @Param("doctorName") String doctorName,
                                  @Param("status") String status);

    int countConflict(@Param("doctorId") Long doctorId,
                      @Param("appointmentTime") LocalDateTime appointmentTime,
                      @Param("excludeId") Long excludeId);
}

