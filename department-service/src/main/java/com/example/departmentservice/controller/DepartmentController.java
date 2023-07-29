package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> get(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.get(id), HttpStatus.OK);
    }

    @GetMapping("/getByCode/{code}")
    public  ResponseEntity<DepartmentDto> getByCode(@PathVariable("code") String code){
        DepartmentDto departmentDto = departmentService.getByCode(code);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.create(departmentDto), HttpStatus.CREATED);
    }
}
