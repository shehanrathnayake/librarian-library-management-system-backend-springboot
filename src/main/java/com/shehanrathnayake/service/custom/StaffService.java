package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.to.StaffTO;

import java.util.List;

public interface StaffService extends SuperService {
    StaffTO saveStaff(StaffTO staffTO);
    void updateStaff(StaffTO staffTO);
    void deleteStaff(String staffId);
    StaffTO getStaffDetails(String staffId);
    List<StaffTO> getAllStaff();
}
