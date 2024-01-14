package com.shehanrathnayake.converter;

import com.shehanrathnayake.util.IssueStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IssueStatusConverter implements Converter<String, IssueStatus> {
    @Override
    public IssueStatus convert(String source) {
        for (IssueStatus status : IssueStatus.values()) {
            if (status.getStatus().equalsIgnoreCase(source)) {
                return status;
            }
        }
        return null;
    }
}
