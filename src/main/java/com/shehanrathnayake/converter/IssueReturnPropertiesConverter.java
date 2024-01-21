package com.shehanrathnayake.converter;

import org.springframework.stereotype.Component;

@Component
public class IssueReturnPropertiesConverter {
    public int convertIdToInt(String issueReturnId) {
        return Integer.parseInt(issueReturnId.substring(2));
    }
    public String covertToString(Integer issueReturnId) {
        return String.format("IR%06d", issueReturnId);
    }

}
