package com.example.demo.entity;

import lombok.Data;

@Data
public class Doctor {
    private Long id;
    private String name;
    private String department;
    private String title;
    private String phone;
    private String description;
}

