package com.msl.ExpenseTracker.repo;

import com.msl.ExpenseTracker.model.Categories;
import com.msl.ExpenseTracker.model.Expenses;

import com.msl.ExpenseTracker.model.Income;
import com.msl.ExpenseTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses,Integer> {
    // Example method to find all expenses by user
    List<Expenses> findByUserId(int id);

    // Example method to find all expenses by category
    List<Expenses> findByCategoryId(int id);

    // Example method to find expenses by user and category
    List<Expenses> findByUserAndCategory(Users user, Categories category);
}
