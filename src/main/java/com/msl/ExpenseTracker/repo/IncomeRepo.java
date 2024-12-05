package com.msl.ExpenseTracker.repo;

import com.msl.ExpenseTracker.model.Income;
import com.msl.ExpenseTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepo extends JpaRepository<Income,Integer> {

    List<Income> findByUserId(int userId);
}
