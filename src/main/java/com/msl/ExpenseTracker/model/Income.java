package com.msl.ExpenseTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incomeId;  // Primary Key with Auto Increment

    // Foreign key to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    @JsonIgnore
    private Users user;

    private int incomeSource;

    private String incomeAmount;

    private LocalDateTime receivedAt;

    private String description;

    private LocalDateTime createdAt;

}
