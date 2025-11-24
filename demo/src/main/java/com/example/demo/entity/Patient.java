package com.example.demo.entity;

import lombok.Data;

@Data
public class Patient {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String phone;
    private String idNumber;
    private String address;
}

