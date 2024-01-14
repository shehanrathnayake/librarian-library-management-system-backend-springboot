package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.User;
import com.shehanrathnayake.util.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUsersByRole(UserRole role);
}
