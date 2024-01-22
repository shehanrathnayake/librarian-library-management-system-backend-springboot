package com.shehanrathnayake.converter;

import org.springframework.stereotype.Component;

@Component
public class EmployeePropertiesConverter {
    public int convertIdToInt(String employeeId) {
        return Integer.parseInt(employeeId.substring(1));
    }
    public String covertToString(Integer employeeId) {
        return String.format("E%06d", employeeId);
    }
}
