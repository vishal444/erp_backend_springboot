package com.test.ana.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private String productName;
    private double existingStock;
    private double sellingPrice;
    private double buyingPrice;
    private String userName;
}
