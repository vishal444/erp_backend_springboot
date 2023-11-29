package com.test.ana.purchases;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class PurchaseRecordDTO implements Serializable {

    private int quantity;

    private double price;

    private double total;

    private Integer product;
}
