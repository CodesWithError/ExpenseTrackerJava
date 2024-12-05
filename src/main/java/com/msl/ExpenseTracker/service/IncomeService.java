package com.msl.ExpenseTracker.service;

import com.msl.ExpenseTracker.enums.ServiceCode;
import com.msl.ExpenseTracker.model.Income;
import com.msl.ExpenseTracker.repo.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepo repo;


    public String addIncome(Income income){
        if(income!=null){
            income.setReceivedAt(LocalDateTime.now());
            income.setCreatedAt(LocalDateTime.now());
            repo.save(income);
            return ServiceCode.INC01.getCode()+" "+ServiceCode.INC01.getMessage();
        }

        return ServiceCode.INC02.getCode()+" "+ServiceCode.INC02.getMessage();
    }

    public List<Income> getIncomeByUserId(int userId) {
        return repo.findByUserId(userId);
    }
}
