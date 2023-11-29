package com.test.ana.pandLAccount;

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
public class ProfitLossAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double salaryPayment;
    private double loanPayment;
    private double rentPayment;
    private double insurance;
    private double electricity;
    private double internet;
    private double taxes;
    private double transport;
    private double cookingGas;
    private double audit;
    private double repairAndMaintenance;
    private double damageOfStock;
    private double depreciation;
    private double badDebtWrittenOff;
    private double lossOnSaleOfFixedAsset;
    private double deliveryCharges;
    private double lossOnSaleOfInvestment;
    private double miscellaneousExpenses;
    private double debitSide;
    private double profitOnSaleOfFixedAsset;
    private double profitOnSaleOfInvestment;
    private double badDebtsRecovered;
    private double interestIncomeLent;
    private double rentReceived;
    private double discountReceived;
    private double miscellaneousIncomes;
    private double creditSide;
    private double netLoss;
    private double netProfit;
    private int year;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
