package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.service.custom.UserService;
import com.shehanrathnayake.service.util.UserRole;
import com.shehanrathnayake.to.UserTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public UserTO saveUser(UserTO userTO) {
        return null;
    }

    @Override
    public void updateUser(UserTO userTO) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserTO getUserDetails(String userId) {
        return null;
    }

    @Override
    public List<UserTO> getAllUsers(UserRole role) {
        return null;
    }
}