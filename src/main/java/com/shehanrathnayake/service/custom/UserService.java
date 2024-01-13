package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.service.util.UserRole;
import com.shehanrathnayake.to.UserTO;

import java.util.List;

public interface UserService extends SuperService {
    UserTO saveUser(UserTO userTO);
    void updateUser(UserTO userTO);
    void deleteUser(String userId);
    UserTO getUserDetails(String userId);
    List<UserTO> getAllUsers(UserRole role);
}
