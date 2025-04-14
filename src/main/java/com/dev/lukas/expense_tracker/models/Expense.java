package com.dev.lukas.expense_tracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "expenses")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   
    private String description;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
