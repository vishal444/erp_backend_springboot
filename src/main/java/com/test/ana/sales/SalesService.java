package com.test.ana.sales;

import com.test.ana.customer.Customer;
import com.test.ana.customer.CustomerRepository;
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
public class SalesService {
    @Autowired
    private final SalesRepository salesRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final SaleRecordRepository saleRecordRepository;
    @Autowired
    private final UserRepository userRepository;

    public void addSales(SaleDTO saleDTO) {
        Date currentDate = new Date();
        Sales sale = new Sales();
        sale.setDate(currentDate);
        sale.setReturnedTotalAmount(0.00);
        sale.setActualSaleAmount(saleDTO.getActualSaleAmount());
        sale.setAdvance(saleDTO.getAdvance());
        double advance = saleDTO.getAdvance();
        sale.setUnit(saleDTO.getUnit());
        if(saleDTO.getCustomerId() != null){
            Customer customer = customerRepository.findById(saleDTO.getCustomerId()).orElse(null);
            sale.setCustomerId(customer);
        }
        User user = userRepository.findByEmail(saleDTO.getUserName()).orElseThrow(() -> new IllegalStateException("no such user"));
        sale.setUser(user);
        sale = salesRepository.save(sale);
        List<SaleRecord> saleRecords = new ArrayList<>();
        double total = 0;
        for (SaleRecordDTO saleRecordDto : saleDTO.getSaleRecordDTOS()) {
            SaleRecord saleRecord = new SaleRecord();
            Product product = productRepository.findById(saleRecordDto.getProduct()).orElse(null);
            double price = saleRecordDto.getQuantity() * product.getSelling_price();
            total = total + price;
            saleRecord.setInitial_quantity(saleRecordDto.getQuantity());
            saleRecord.setCurrent_quantity(saleRecordDto.getQuantity());
            saleRecord.setInitialTotal(price);
            saleRecord.setProduct(product);
            saleRecord.setDate(currentDate);
            saleRecord.setSales(sale);
            saleRecord.setUser(user);
            saleRecord.setCurrentTotal(price);
            saleRecord = saleRecordRepository.save(saleRecord);
            saleRecords.add(saleRecord);
        }
        sale.setOutstandingAmount(total - advance);
        sale.setGrandTotal(advance);
        salesRepository.save(sale);
    }

    public Sales getSalesDetailById(Long id) {
        Sales item = salesRepository.findById(id).orElseThrow(() -> new IllegalStateException("no such sales"));
        if (item != null) {
            return item;
        }
        return null;
    }

    public List<Object[]> getGraphByProduct(Integer id, String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user for graph"));
        return saleRecordRepository.getGraphByProduct(id,user);
    }

    public List<Object[]> getGraphAllSales(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user for graph"));
        return saleRecordRepository.getTotalQuantitiesGraph(user);
    }

    public List<Sales> getAllSales(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return salesRepository.findAllSalesByUser(user);
    }

    public Sales getById(Long id, String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return salesRepository.findByIdAndUser(id, user);
    }

    @Transactional
    public void updateSalesReturn(Long salesId, Integer product_id, String userName, Double returnQuantity, Double returnedAmount) {
        // double amount = 0.00;
        double totalReturnedAmount = 0.00;
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        Sales sales = salesRepository.findByIdAndUser(salesId, user);
        SaleRecord saleRecord = saleRecordRepository.getSaleRecord(salesId, product_id);
        if (saleRecord != null) {
            saleRecord.setReturnedQuantity(saleRecord.getReturnedQuantity() + returnQuantity);
            double quantityAfterReturn = saleRecord.getCurrent_quantity() - returnQuantity;
            saleRecord.setCurrent_quantity(quantityAfterReturn);
            saleRecord.setReturnedAmount(saleRecord.getReturnedAmount() + returnedAmount);
            saleRecord.setCurrentTotal(saleRecord.getCurrentTotal()- returnedAmount);
        }
        if (sales != null) {
            totalReturnedAmount = sales.getReturnedTotalAmount() + returnedAmount;
            sales.setReturnedTotalAmount(totalReturnedAmount);
        }
    }

    public List<Sales> getAllOutstandingSales(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        return salesRepository.getAllOutstandingSales(user);
    }

    @Transactional
    public void updateSalesAdvance(Long salesId, String userName, Double nextAdvance) {
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new IllegalStateException("no such user"));
        Sales record = salesRepository.findByIdAndUser(salesId, user);
        if (record != null) {
            record.setGrandTotal(record.getGrandTotal() + nextAdvance);
            record.setOutstandingAmount(record.getOutstandingAmount() - nextAdvance);
            record.setAdvance(record.getAdvance() + nextAdvance);
        }
    }
}
