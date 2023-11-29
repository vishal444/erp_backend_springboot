package com.test.ana.purchases;

import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private double actualPurchaseAmount;

    private double grandTotal;

    private double totalAmountRefunded;

    private double directExpense;

    private double partPayment;

    private double outstandingAmount;
    // TODO vendor detail

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseRecord> purchaseRecords;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
