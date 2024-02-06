package com.shehanrathnayake.converter;

import com.shehanrathnayake.util.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserPropertiesConverter implements Converter<String, UserRole> {

    @Override
    public UserRole convert(String source) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equalsIgnoreCase(source)) {
                return userRole;
            }
        }
        return null;
    }

    public int convertIdToInt(String userId) {
        return Integer.parseInt(userId.substring(1));
    }

    public String convertIdToString(int userId) {
        return String.format("U%06d",userId);
    }
}
