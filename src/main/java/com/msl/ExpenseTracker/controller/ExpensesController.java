package com.msl.ExpenseTracker.controller;

import com.msl.ExpenseTracker.model.Expenses;
import com.msl.ExpenseTracker.service.ExpensesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/eta")
public class ExpensesController {

    @Autowired
    ExpensesService service;

    @PostMapping("/expenses")
    public String addExpenses(@RequestBody Expenses expenses) {
        log.info("Adding expenses: {}", expenses);
        return service.addExpenses(expenses);
    }

    // Get expenses by user
    @GetMapping("/userExpenses/{userId}")
    public List<Expenses> getExpensesByUser(@PathVariable int userId) {
        log.info("Fetching expenses for user ID: {}", userId);
        return service.getExpensesByUser(userId);
    }

    // Get expenses by category
    @GetMapping("/categoryExpenses/{categoryId}")
    public List<Expenses> getExpensesByCategory(@PathVariable int categoryId) {
        log.info("Fetching expenses for category ID: {}", categoryId);
        return service.getExpensesByCategory(categoryId);
    }
}
