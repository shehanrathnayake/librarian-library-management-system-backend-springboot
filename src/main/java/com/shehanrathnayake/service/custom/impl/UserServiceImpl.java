package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.entity.User;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.UserRepository;
import com.shehanrathnayake.service.custom.UserService;
import com.shehanrathnayake.service.util.UserTransformer;
import com.shehanrathnayake.to.UserTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTransformer transformer;

    public UserServiceImpl(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.transformer = userTransformer;
    }

    @Override
    public UserTO saveUser(UserTO userTO) {
        User user = transformer.fromUserTO(userTO);
        User savedUser = userRepository.save(user);
        return transformer.toUserTO(savedUser);
    }

    @Override
    public void updateUser(UserTO userTO) {
        userRepository.findById(getIdNumberValue(userTO.getId())).orElseThrow(() -> new AppException(404, "No user found with this ID"));
        User user = transformer.fromUserTO(userTO);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(getIdNumberValue(userId)).orElseThrow(() -> new AppException(404, "No user found with this ID"));
        userRepository.delete(user);
    }

    @Override
    public UserTO getUserDetails(String userId) {
        Optional<User> optUser = userRepository.findById(getIdNumberValue(userId));
        if (optUser.isEmpty()) throw new AppException(404, "No user found associated with the ID");
        return transformer.toUserTO(optUser.get());
    }

    @Override
    public List<UserTO> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return transformer.toUserTOList(usersList);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loggedUser = userRepository.findById(getIdNumberValue(username)).orElseThrow(() -> new AppException(404, "User not found"));
        return transformer.fromUserToUserDetails(loggedUser);
    }

    private int getIdNumberValue(String userId) {
        return Integer.parseInt(userId.substring(1));
    }
}
