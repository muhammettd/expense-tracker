package com.project.expensetracker_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double education;
    private Double shopping;
    private Double clothes;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Expense() {

    }

    public Expense(Double education, Double shopping, Double clothes, LocalDateTime createdDate, Customer customer) {
        this.education = education;
        this.shopping = shopping;
        this.clothes = clothes;
        this.createdDate = createdDate;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public Double getEducation() {
        return education;
    }

    public Double getShopping() {
        return shopping;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getClothes() {
        return clothes;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
