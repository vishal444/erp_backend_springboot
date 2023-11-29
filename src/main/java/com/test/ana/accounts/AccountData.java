package com.test.ana.accounts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class AccountData {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;
    private String description;
    private Date date;
    private Double debit;
    private Double credit;
    private Double balance;

    public AccountData() {
    }

    public AccountData(Integer id, String description, Date date, Double debit, Double credit, Double balance) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
