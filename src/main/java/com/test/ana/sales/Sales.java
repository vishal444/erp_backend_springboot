package com.test.ana.sales;

import com.test.ana.customer.Customer;
import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    private String unit;

    private double actualSaleAmount;

    private double grandTotal;

    private double advance;

    private double outstandingAmount;

    private double returnedTotalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private Customer customerId;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_id")
    private List<SaleRecord> saleRecords;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
