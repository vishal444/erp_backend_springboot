package com.test.ana.purchases;

import com.test.ana.product.Product;
import com.test.ana.product.ProductRepository;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {
    @Autowired
    private final PurchaseRepository purchaseRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final PurchaseRecordRepository purchaseRecordRepository;
    @Autowired
    private final UserRepository userRepository;

    public void addPurchase(PurchaseDTO purchaseDTO) {
        Date currentDate = new Date();
        Purchase purchases = new Purchase();
        purchases.setTotalAmountRefunded(0.00);
        purchases.setDate(currentDate);
        purchases.setDirectExpense(purchaseDTO.getDirectExpense());
        purchases.setActualPurchaseAmount(purchaseDTO.getActualPurchaseAmount());
        purchases.setPartPayment(purchaseDTO.getPartPayment());
        double partPayment = purchaseDTO.getPartPayment();
        purchases = purchaseRepository.save(purchases);
        User user  = userRepository.findByEmail(purchaseDTO.getUserName()).orElseThrow(() -> new IllegalStateException("no such user"));
        purchases.setUser(user);
        List<PurchaseRecord> purchaseRecords = new ArrayList<>();
        double total = 0;
        for (PurchaseRecordDTO purchaseRecordDTO : purchaseDTO.getPurchaseRecordDTOS()) {
            PurchaseRecord purchaseRecord = new PurchaseRecord();
            Product product = productRepository.findById(purchaseRecordDTO.getProduct()).orElse(null);
            purchaseRecord.setProduct(product);
            purchaseRecord.setPrice(purchaseRecordDTO.getPrice());
            purchaseRecord.setInitial_quantity(purchaseRecordDTO.getQuantity());
            purchaseRecord.setCurrent_quantity(purchaseRecordDTO.getQuantity());
            double amount = purchaseRecordDTO.getPrice() * purchaseRecordDTO.getQuantity();
            purchaseRecord.setInitialTotal(amount);
            purchaseRecord.setCurrentTotal(amount);
            purchaseRecord.setDate(currentDate);
            total = total + amount;
            purchaseRecord.setPurchase(purchases);
            purchaseRecord.setUser(user);
            purchaseRecord = purchaseRecordRepository.save(purchaseRecord);
            purchaseRecords.add(purchaseRecord);
        }
        purchases.setGrandTotal(partPayment);
        purchases.setOutstandingAmount(total - partPayment);
        purchaseRepository.save(purchases);
    }

    public List<Purchase> getAllPurchase(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return purchaseRepository.findAllPurchaseByUser(user);
    }
    @Transactional
    public void updatePurchaseReturn(Long purchaseId, Integer product_id, String userName, Double returnQuantity, Double returnedAmount) {
        // double amount = 0.00;
        double totalReturnedAmount = 0.00;
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        Purchase purchases = purchaseRepository.findByIdAndUser(purchaseId, user);
        PurchaseRecord  purchaseRecord = purchaseRecordRepository.getPurchaseRecord(purchaseId,product_id);
        if(purchaseRecord != null){
            purchaseRecord.setQuantityReturned(purchaseRecord.getQuantityReturned() + returnQuantity);
            double quantityAfterReturn = purchaseRecord.getCurrent_quantity() - returnQuantity;
            purchaseRecord.setCurrent_quantity(quantityAfterReturn);
            purchaseRecord.setAmountRefunded(purchaseRecord.getAmountRefunded() + returnedAmount);
            purchaseRecord.setCurrentTotal(purchaseRecord.getCurrentTotal() - returnedAmount);
        }
        if(purchases != null){
            totalReturnedAmount = purchases.getTotalAmountRefunded() + returnedAmount;
            purchases.setTotalAmountRefunded(totalReturnedAmount);
        }
    }
    @Transactional
    public void updatePurchasesPartPayment(Long purchaseId, Double nextPartPayment, String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        Purchase record = purchaseRepository.findByIdAndUser(purchaseId, user);
        if (record != null) {
            record.setGrandTotal(record.getGrandTotal() + nextPartPayment);
            record.setOutstandingAmount(record.getOutstandingAmount() - nextPartPayment);
            record.setPartPayment(record.getPartPayment() + nextPartPayment);
        }
    }
    public Purchase getByIdAndUser(Long purchaseId, String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("No such user"));
        Purchase item = purchaseRepository.findByIdAndUser(purchaseId, user);
        if(item!=null) {
            return item;
        }
        return null;
    }
    public List<Purchase> getAllOutstandingPurchases(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return purchaseRepository.getAllOutstandingPurchases(user);
    }
}
