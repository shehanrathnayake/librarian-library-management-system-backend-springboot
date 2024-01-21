package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.to.EmployeeTO;

import java.util.List;

public interface EmployeeService extends SuperService {
    EmployeeTO saveEmployee(EmployeeTO employeeTO);
    void updateEmployee(EmployeeTO employeeTO);
    void deleteEmployee(String employeeId);
    EmployeeTO getEmployeeDetails(String employeeId);
    List<EmployeeTO> getAllEmployees();
}
