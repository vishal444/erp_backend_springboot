package com.test.ana.purchases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.ana.product.Product;
import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "purchase_record")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double initial_quantity;

    private Date date;

    private double current_quantity;

    private double price;

    private double initialTotal;

    private double currentTotal;

    private double quantityReturned;

    private double amountRefunded;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    @JsonIgnore
    private Purchase purchase;

    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
