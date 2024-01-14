package com.shehanrathnayake.converter;

import com.shehanrathnayake.util.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter implements Converter<String, UserRole> {
    @Override
    public UserRole convert(String source) {
        for (UserRole role : UserRole.values()) {
            if (role.getRole().equalsIgnoreCase(source)) {
                return role;
            }
        }
        return null;
    }
}
