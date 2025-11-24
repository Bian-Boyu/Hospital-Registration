package com.example.demo.service;

import com.example.demo.entity.Patient;

import java.util.List;

public interface PatientService {

    void create(Patient patient);

    void update(Patient patient);

    void delete(Long id);

    Patient findById(Long id);

    List<Patient> list(String name, String phone);
}

