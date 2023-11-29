package com.test.ana.monthlyIncomeExpenses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeAndExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double electricity;
    private double internet;
    private double insurance;
    private double taxes;
    private double transport;
    private double cookingGas;
    private double audit;
    private double repairAndMaintenance;
    private double deliveryCharges;
    private double damageOfStock;
    private double lossSaleFixedAsset;
    private double badDebtWrittenOff;
    private double capitalDrawings;
    private double miscellaneousExpenses;
    private double interestIncomeLent;
    private double rentReceived;
    private double discountReceived;
    private double badDebtsRecovered;
    private double profitSaleFixedAsset;
    private double miscellaneousIncomes;
    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING) //To properly serialize and deserialize the category field, you can use the @JsonFormat annotation from the Jackson library
    private MonthList monthList;
    private Date date;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
