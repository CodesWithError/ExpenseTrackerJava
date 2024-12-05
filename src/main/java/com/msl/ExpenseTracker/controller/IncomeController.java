package com.msl.ExpenseTracker.controller;

import com.msl.ExpenseTracker.service.IncomeService;
import lombok.extern.slf4j.Slf4j;
import com.msl.ExpenseTracker.model.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/eta")
public class IncomeController {

    @Autowired
    IncomeService service;

    @PostMapping("/income")
    public String addIncome(@RequestBody Income income) {
        log.info("Adding income: {}", income);
        return service.addIncome(income);
    }

    @GetMapping("/income/{userId}")
    public List<Income> getUserIncome(@PathVariable int userId) {
        log.info("Fetching income for user ID: {}", userId);
        return service.getIncomeByUserId(userId);
    }
}
