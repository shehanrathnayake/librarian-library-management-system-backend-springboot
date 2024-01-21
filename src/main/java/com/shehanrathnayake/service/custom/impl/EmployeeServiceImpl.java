package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.converter.EmployeePropertiesConverter;
import com.shehanrathnayake.entity.Employee;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.EmployeeRepository;
import com.shehanrathnayake.service.custom.EmployeeService;
import com.shehanrathnayake.service.util.EmployeeTransformer;
import com.shehanrathnayake.to.EmployeeTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTransformer transformer;
    private final EmployeePropertiesConverter converter;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeTransformer transformer, EmployeePropertiesConverter converter) {
        this.employeeRepository = employeeRepository;
        this.transformer = transformer;
        this.converter = converter;
    }

    @Override
    public EmployeeTO saveEmployee(EmployeeTO employeeTO) {
        Employee employee = transformer.fromEmployeeTO(employeeTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return transformer.toEmployeeTO(savedEmployee);
    }

    @Override
    public void updateEmployee(EmployeeTO employeeTO) {
        employeeRepository.findById(converter.convertIdToInt(employeeTO.getId()));
        Employee employee = transformer.fromEmployeeTO(employeeTO);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String staffId) {
        Employee employee = employeeRepository.findById(converter.convertIdToInt(staffId)).orElseThrow(() -> new AppException(404, "No staff found with this ID"));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeTO getEmployeeDetails(String staffId) {
        Employee employee = employeeRepository.findById(converter.convertIdToInt(staffId)).orElseThrow(() -> new AppException(404, "No staff found associated with this ID"));
        return transformer.toEmployeeTO(employee);
    }

    @Override
    public List<EmployeeTO> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return transformer.toEmployeeTOList(employeeList);
    }
}
