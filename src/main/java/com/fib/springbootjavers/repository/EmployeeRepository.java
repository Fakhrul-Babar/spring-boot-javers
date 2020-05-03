package com.fib.springbootjavers.repository;

import com.fib.springbootjavers.domain.Employee;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

@JaversSpringDataAuditable
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
