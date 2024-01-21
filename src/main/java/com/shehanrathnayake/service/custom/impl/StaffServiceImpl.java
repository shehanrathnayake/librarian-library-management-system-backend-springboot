package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.converter.StaffPropertiesConverter;
import com.shehanrathnayake.entity.Staff;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.StaffRepository;
import com.shehanrathnayake.service.custom.StaffService;
import com.shehanrathnayake.service.util.StaffTransformer;
import com.shehanrathnayake.to.StaffTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffTransformer transformer;
    private final StaffPropertiesConverter converter;

    public StaffServiceImpl(StaffRepository staffRepository, StaffTransformer transformer, StaffPropertiesConverter converter) {
        this.staffRepository = staffRepository;
        this.transformer = transformer;
        this.converter = converter;
    }

    @Override
    public StaffTO saveStaff(StaffTO staffTO) {
        Staff staff = transformer.fromStaffTO(staffTO);
        Staff savedStaff = staffRepository.save(staff);
        return transformer.toStaffTO(savedStaff);
    }

    @Override
    public void updateStaff(StaffTO staffTO) {
        staffRepository.findById(converter.convertIdToInt(staffTO.getId()));
        Staff staff = transformer.fromStaffTO(staffTO);
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(String staffId) {
        Staff staff = staffRepository.findById(converter.convertIdToInt(staffId)).orElseThrow(() -> new AppException(404, "No staff found with this ID"));
        staffRepository.delete(staff);
    }

    @Override
    public StaffTO getStaffDetails(String staffId) {
        Staff staff = staffRepository.findById(converter.convertIdToInt(staffId)).orElseThrow(() -> new AppException(404, "No staff found associated with this ID"));
        return transformer.toStaffTO(staff);
    }

    @Override
    public List<StaffTO> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        return transformer.toStaffTOList(staffList);
    }
}
