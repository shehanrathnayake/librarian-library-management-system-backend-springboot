package com.shehanrathnayake.converter;

import org.springframework.stereotype.Component;

@Component
public class UserPropertiesConverter{

    public int convertIdToInt(String userId) {
        return Integer.parseInt(userId.substring(1));
    }

    public String convertIdToString(int userId) {
        return String.format("U%06d",userId);
    }
}
