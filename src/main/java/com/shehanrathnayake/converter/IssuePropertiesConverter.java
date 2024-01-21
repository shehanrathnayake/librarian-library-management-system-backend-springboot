package com.shehanrathnayake.converter;

import com.shehanrathnayake.util.IssueStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IssuePropertiesConverter implements Converter<String, IssueStatus> {
    @Override
    public IssueStatus convert(String source) {
        for (IssueStatus status : IssueStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(source)) {
                return status;
            }
        }
        return null;
    }

    public int convertIdToInt(String issueId) {
        return Integer.parseInt(issueId.substring(1));
    }
    public String covertToString(Integer issueId) {
        return String.format("I%06d", issueId);
    }

}
