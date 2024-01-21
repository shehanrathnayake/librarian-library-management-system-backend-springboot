package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
