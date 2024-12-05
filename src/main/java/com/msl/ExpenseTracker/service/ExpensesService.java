package com.msl.ExpenseTracker.service;

import com.msl.ExpenseTracker.enums.ServiceCode;
import com.msl.ExpenseTracker.model.Categories;
import com.msl.ExpenseTracker.model.Expenses;
import com.msl.ExpenseTracker.model.Income;
import com.msl.ExpenseTracker.model.Users;
import com.msl.ExpenseTracker.repo.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {

    @Autowired
    ExpensesRepo repo;

    public String addExpenses(Expenses expenses){
        if(expenses!=null){
            expenses.setExpenseDate(LocalDateTime.now());
            expenses.setCreatedAt(LocalDateTime.now());
            repo.save(expenses);
            return ServiceCode.EXP02.getCode()+" "+ServiceCode.EXP01.getMessage();
        }
        return ServiceCode.EXP02.getCode()+" "+ServiceCode.EXP02.getMessage();
    }



    // Get expenses by user
    public List<Expenses> getExpensesByUser(int id) {
        return repo.findByUserId(id);
    }

    // Get expenses by category
    public List<Expenses> getExpensesByCategory(int id) {
        return repo.findByCategoryId(id);
    }

    // Get expenses by user and category
    public List<Expenses> getExpensesByUserAndCategory(Users user, Categories category) {
        return repo.findByUserAndCategory(user, category);
    }

}
