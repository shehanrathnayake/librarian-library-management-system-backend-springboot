package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.StaffPropertiesConverter;
import com.shehanrathnayake.entity.Staff;
import com.shehanrathnayake.to.StaffTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StaffTransformer {
    private final ModelMapper mapper;

    public StaffTransformer(ModelMapper mapper, StaffPropertiesConverter staffPropsConverter) {
        this.mapper = mapper;

        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? Integer.parseInt(ctx.getSource().substring(1)) : null);
        mapper.typeMap(Integer.class, String.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? staffPropsConverter.covertToString(ctx.getSource()) : null);
    }
    public Staff fromStaffTO(StaffTO staffTO) {
        return mapper.map(staffTO, Staff.class);
    }
    public StaffTO toStaffTO(Staff staff) {
        return mapper.map(staff, StaffTO.class);
    }
    public List<StaffTO> toStaffTOList(List<Staff> staffList) {
        return staffList.stream().map(this::toStaffTO).collect(Collectors.toList());
    }
}
