package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto create(DepartmentDto departmentDto);
    DepartmentDto getByCode(String code);
    DepartmentDto get(Long id);
}
