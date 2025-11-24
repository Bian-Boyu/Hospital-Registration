package com.example.demo.common;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusiness(BusinessException ex) {
        return ApiResponse.fail(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    public ApiResponse<Void> handleValidation(Exception ex) {
        return ApiResponse.fail(400, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Void> handleBody(HttpMessageNotReadableException ex) {
        return ApiResponse.fail(400, "请求体格式有误");
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleOthers(Exception ex) {
        return ApiResponse.fail(500, ex.getMessage());
    }
}

