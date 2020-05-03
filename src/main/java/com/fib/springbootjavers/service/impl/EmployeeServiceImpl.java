package com.fib.springbootjavers.service.impl;

import com.fib.springbootjavers.domain.Employee;
import com.fib.springbootjavers.repository.EmployeeRepository;
import com.fib.springbootjavers.service.EmployeeService;
import org.javers.spring.annotation.JaversAuditable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @JaversAuditable
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployee(long id) {
        return employeeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Employee> fetchEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @JaversAuditable
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
