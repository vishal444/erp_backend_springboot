package com.test.ana.bSheet;

import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double furniture;               ////
    private double machinery;               /////
    private double land;                    ///////
    private double building;                /////// TODO - value after depreciation
    private double equipments;              /////
    private double motorVehicles;           ////
    private double investments;
    private double cashInHand;
    private double cashAtBank;
    private double closingStock;           // is this needed ??
    private double prepaidExpenses;       // extra paid salary, rent, loan payment etc
    private double outstandingIncomes;   // outstanding incomes or pattu paisa kittanullathu
    private double debtors;             // money people owe you
    //////////// LIABILITIES //////////////////
    private double capital;           // add net profit or subtract net loss from P&L,also subtract from capital the money taken from capital by the owner
    private double creditors;        // money to be paid back after purchase of goods
    private double loans;
    private double overDrafts;
    private double advancesReceived;
    private double billsPayable;
    private double outstandingExpenses; // like salary,rent,loan yet to be paid
    private String month;
    private int year;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
