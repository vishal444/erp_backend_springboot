package com.test.ana.sales;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class SaleRecordDTO implements Serializable {
    private Date sales_date;
    private int total;
    private Integer product;
    private String userName;
    private float quantity;
}
