
package com.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountHolder;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    public Account() {
    }

    public Account(
            String accountHolder,
            String accountNumber,
            BigDecimal balance
    ) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
