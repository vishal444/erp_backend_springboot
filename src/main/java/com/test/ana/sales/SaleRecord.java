package com.test.ana.sales;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.ana.product.Product;
import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "sale_record")
@NoArgsConstructor
@AllArgsConstructor
public class SaleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double initial_quantity;

    private double current_quantity;

    private double returnedQuantity;

    private double returnedAmount;

    private double currentTotal;

    private double initialTotal;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_id")
    @JsonIgnore
    @ToString.Exclude
    private Sales sales;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
