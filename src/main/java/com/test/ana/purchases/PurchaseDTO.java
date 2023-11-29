package com.test.ana.purchases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO implements Serializable {
    private Long id;
    private Date date;
    private double grandTotal;
    private double actualPurchaseAmount;
    private double partPayment;
    private double quantityReturned;
    private double amountRenumerated;
    private double directExpense;
    private String userName;
    private List<PurchaseRecordDTO> purchaseRecordDTOS;
}
