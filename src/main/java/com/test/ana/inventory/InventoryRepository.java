package com.test.ana.inventory;

import com.test.ana.product.Product;
import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,   Long> {
    @Query("SELECT i FROM Inventory i WHERE i.product_id = :product_id")
    Inventory findByProductId(Product product_id);

    @Query("SELECT SUM(i.quantity * i.buying_price) FROM Inventory i WHERE i.user = :user")
    Double getStock(@Param("user") User user);
    @Query("SELECT i FROM Inventory i WHERE i.user = :user")
    List<Inventory> findInventoryByUser(@Param("user") User user);
    @Query("SELECT i FROM Inventory i WHERE i.product_id = :product_id AND i.user = :user")
    Inventory findByProductIdAndUser(@Param("product_id") Product productId,@Param("user") User user);

}
