package com.test.ana.tradingAccount;

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
public class TradingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double netPurchase;

    private Double netSales;

    private Double closingStock;

    private Integer year;

    private Double openingStock;

    private Double grossProfit;

    private Double grossLoss;

    private Double directExpenses;

    private Double netSalesReturns;

    private Double netPurchaseReturns;

    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
