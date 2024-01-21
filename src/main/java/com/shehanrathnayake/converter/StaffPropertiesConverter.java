package com.shehanrathnayake.converter;

import org.springframework.stereotype.Component;

@Component
public class StaffPropertiesConverter {
    public int convertIdToInt(String staffId) {
        return Integer.parseInt(staffId.substring(1));
    }
    public String covertToString(Integer staffId) {
        return String.format("S%06d", staffId);
    }
}
