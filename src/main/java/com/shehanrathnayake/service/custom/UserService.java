package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.to.UserTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends SuperService, UserDetailsService {
    UserTO saveUser(UserTO userTO);
    void updateUser(UserTO userTO);
    void deleteUser(String userId);
    UserTO getUserDetails(String userId);
    List<UserTO> getAllUsers();
}
