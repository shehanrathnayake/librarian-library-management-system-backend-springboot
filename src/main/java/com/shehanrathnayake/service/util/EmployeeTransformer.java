package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.EmployeePropertiesConverter;
import com.shehanrathnayake.entity.Employee;
import com.shehanrathnayake.to.EmployeeTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeTransformer {
    private final ModelMapper mapper;

    public EmployeeTransformer(ModelMapper mapper, EmployeePropertiesConverter employeePropsConverter) {
        this.mapper = mapper;

        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? employeePropsConverter.convertIdToInt(ctx.getSource()) : null);
        mapper.typeMap(Integer.class, String.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? employeePropsConverter.covertToString(ctx.getSource()) : null);
    }
    public Employee fromEmployeeTO(EmployeeTO employeeTO) {
        return mapper.map(employeeTO, Employee.class);
    }
    public EmployeeTO toEmployeeTO(Employee employee) {
        return mapper.map(employee, EmployeeTO.class);
    }
    public List<EmployeeTO> toEmployeeTOList(List<Employee> employeeList) {
        return employeeList.stream().map(this::toEmployeeTO).collect(Collectors.toList());
    }
}
