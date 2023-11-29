package com.test.ana.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO implements Serializable {
    private long id;    //TOD0
    private String unit;
    private double grandTotal;
    private double actualSaleAmount;
    private double advance;
    private double returnedTotalAmount;
    private String userName;
    private Integer customerId;
    private List<SaleRecordDTO> saleRecordDTOS;
}
