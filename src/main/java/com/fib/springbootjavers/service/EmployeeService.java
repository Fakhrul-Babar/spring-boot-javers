package com.fib.springbootjavers.service;

import com.fib.springbootjavers.domain.Employee;

import java.util.List;

public interface EmployeeService {

    Employee update(Employee employee);
    Employee findEmployee(long id);
    List<Employee> fetchEmployees();
    Employee save(Employee employee);
}
