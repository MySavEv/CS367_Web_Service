package com.saveohm.hrservice.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saveohm.hrservice.model.Employee;
import com.saveohm.hrservice.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getEmployees(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String position,
            @RequestParam(defaultValue = "0") Float minSalary,
            @RequestParam(defaultValue = "99999999999") Float maxSalary
    ) {
        // ค้นหาตามทุกเงื่อนไขที่ระบุใน query string
        return employeeRepository.search(title, position, minSalary, maxSalary);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(
            @RequestBody Employee entity,
            @PathVariable long id
    ) {
        entity.setId(id);
        return employeeRepository.save(entity);
    }
    
}
