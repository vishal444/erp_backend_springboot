package com.test.ana.inventory;

import com.test.ana.product.Product;
import com.test.ana.user.User;
import com.test.ana.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;
    @Autowired
    private final UserRepository userRepository;

    private Double  quantityForUpdate;
    public InventoryService(InventoryRepository inventoryRepository1,UserRepository userRepository1){
        this.inventoryRepository = inventoryRepository1;
        this.userRepository = userRepository1;
    }
    @Transactional
    public void updatePurchaseInventory(Product product_id, String userName, Double quantity,Double price) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        Inventory item = inventoryRepository.findByProductIdAndUser(product_id, user);
        if(item!=null) {
            quantityForUpdate = item.getQuantity() + quantity;
            item.setQuantity(quantityForUpdate);
            item.setBuying_price(price);
        }
    }

    @Transactional
    public void updateInventory(Product product_id, Double quantity,Double buying_price) {
        Inventory item = inventoryRepository.findByProductId(product_id);
        if(item!=null) {
            quantityForUpdate = item.getQuantity() + quantity;
            item.setQuantity(quantityForUpdate);
            item.setBuying_price(buying_price);
        }
    }
    @Transactional
    public void updateSaleInventory(Product product_id, String userName, Double quantity) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        Inventory item = inventoryRepository.findByProductIdAndUser(product_id, user);
        if(item!=null) {
            quantityForUpdate = item.getQuantity() - quantity;
            item.setQuantity(quantityForUpdate);
        }
//         inventoryRepository.save(item); ???
    }

    public List<Inventory> getAllInventory(String userName) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        return inventoryRepository.findInventoryByUser(user);
    }
    @Transactional
    public void updatePurchaseReturn(Product productId, String userName, Double quantity) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        Inventory item = inventoryRepository.findByProductIdAndUser(productId, user);
        if(item!=null){
            quantityForUpdate = item.getQuantity() - quantity;
            item.setQuantity(quantityForUpdate);
        }
    }
    @Transactional
    public void updateSaleReturn(Product productId, String userName, Double quantity) {
        User user = userRepository.findByEmail(userName).orElseThrow(()->new IllegalStateException("no such user"));
        Inventory item = inventoryRepository.findByProductIdAndUser(productId, user);
        if(item != null){
            quantityForUpdate = item.getQuantity() + quantity;
            item.setQuantity(quantityForUpdate);
        }
    }
}
