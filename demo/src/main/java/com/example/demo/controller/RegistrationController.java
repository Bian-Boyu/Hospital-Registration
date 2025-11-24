package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.entity.Registration;
import com.example.demo.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ApiResponse<List<Registration>> list(@RequestParam(required = false) String patientName,
                                                @RequestParam(required = false) String doctorName,
                                                @RequestParam(required = false) String status) {
        return ApiResponse.success(registrationService.list(patientName, doctorName, status));
    }

    @GetMapping("/{id}")
    public ApiResponse<Registration> detail(@PathVariable Long id) {
        return ApiResponse.success(registrationService.findById(id));
    }

    @PostMapping
    public ApiResponse<Void> create(@RequestBody Registration registration) {
        registrationService.create(registration);
        return ApiResponse.success("挂号成功", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @RequestBody Registration registration) {
        registration.setId(id);
        registrationService.update(registration);
        return ApiResponse.success("更新成功", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        registrationService.delete(id);
        return ApiResponse.success("取消成功", null);
    }
}

