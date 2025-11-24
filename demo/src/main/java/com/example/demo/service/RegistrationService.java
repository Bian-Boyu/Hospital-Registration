package com.example.demo.service;

import com.example.demo.entity.Registration;

import java.util.List;

public interface RegistrationService {

    void create(Registration registration);

    void update(Registration registration);

    void delete(Long id);

    Registration findById(Long id);

    List<Registration> list(String patientName, String doctorName, String status);
}

