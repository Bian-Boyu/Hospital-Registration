package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.entity.Doctor;
import com.example.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ApiResponse<List<Doctor>> list(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String department) {
        return ApiResponse.success(doctorService.list(name, department));
    }

    @GetMapping("/{id}")
    public ApiResponse<Doctor> detail(@PathVariable Long id) {
        return ApiResponse.success(doctorService.findById(id));
    }

    @PostMapping
    public ApiResponse<Void> create(@RequestBody Doctor doctor) {
        doctorService.create(doctor);
        return ApiResponse.success("新增成功", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        doctorService.update(doctor);
        return ApiResponse.success("更新成功", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ApiResponse.success("删除成功", null);
    }
}

