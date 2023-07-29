package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final APIClient apiClient;

//    private final RestTemplate restTemplate;
//    private final WebClient webClient;



    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }


    //with SpringCloud OpenFeign
    @Override
    public APIResponseDto get(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentId());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }


//    //with WebClient
//    @Override
//    public APIResponseDto get(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Not found"));
//
//        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/v1/department/"
//                        + employee.getDepartmentId())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployeeDto(employeeDto);
//        apiResponseDto.setDepartmentDto(departmentDto);
//
//        return apiResponseDto;
//    }


//    //with REST template
//    @Override
//    public APIResponseDto get(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Not found"));
//
//        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/department/"
//                + employee.getDepartmentId(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployeeDto(employeeDto);
//        apiResponseDto.setDepartmentDto(departmentDto);
//
//        return apiResponseDto;
//    }
}
