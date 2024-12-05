package com.msl.ExpenseTracker.model;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient
    private String username;
    private String mobile;
    private String email;
    private String password;

    @Transient
    private String confirmPassword;
    private String totalBalance;
    private String totalIncome;
    private String totalExpense;
    private int createdBy;
    private LocalDateTime createdAt;
    private Integer modifiedBy;
    private LocalDateTime modifiedAt;
    private boolean isActive;
}
