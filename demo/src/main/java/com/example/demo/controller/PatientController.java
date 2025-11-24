package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Validated
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ApiResponse<List<Patient>> list(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String phone) {
        return ApiResponse.success(patientService.list(name, phone));
    }

    @GetMapping("/{id}")
    public ApiResponse<Patient> detail(@PathVariable Long id) {
        return ApiResponse.success(patientService.findById(id));
    }

    @PostMapping
    public ApiResponse<Void> create(@RequestBody Patient patient) {
        patientService.create(patient);
        return ApiResponse.success("新增成功", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody Patient patient) {
        patient.setId(id);
        patientService.update(patient);
        return ApiResponse.success("更新成功", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ApiResponse.success("删除成功", null);
    }
}

