package com.example.demo.service;

import com.example.demo.entity.Doctor;

import java.util.List;

public interface DoctorService {

    void create(Doctor doctor);

    void update(Doctor doctor);

    void delete(Long id);

    Doctor findById(Long id);

    List<Doctor> list(String name, String department);
}

