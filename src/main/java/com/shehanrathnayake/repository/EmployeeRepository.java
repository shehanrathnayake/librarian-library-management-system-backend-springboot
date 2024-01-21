package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
