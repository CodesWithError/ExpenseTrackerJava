package com.msl.ExpenseTracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msl.ExpenseTracker.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
//	@Query("SELECT u FROM Users u WHERE u.email=email")
	Users findByEmail(String email);
	Users findByMobile(String mobile);
}
