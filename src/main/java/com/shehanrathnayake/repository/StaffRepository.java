package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}
