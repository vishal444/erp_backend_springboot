package com.test.ana.purchases;

import com.test.ana.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT SUM(p.grandTotal) FROM Purchase p WHERE p.user = :user")
    Double getNetPurchaseAmount(@Param("user") User user);

    @Query("SELECT SUM(p.totalAmountRefunded) FROM Purchase p WHERE p.user = :user")
    Double getNetRefundedPurchaseAmount(@Param("user") User user);
    @Query("SELECT p FROM Purchase p WHERE p.user = :user")
    List<Purchase> findAllPurchaseByUser(@Param("user") User user);

//    @Query("SELECT p FROM Purchases p WHERE p.outstandingAmount != 0 AND p.user = :user")
//    List<Purchases> getAllOutstandingPurchases(@Param("user") User user);
    @Query("SELECT p FROM Purchase p WHERE p.outstandingAmount <> 0 AND p.outstandingAmount >= 0 AND p.user = :user")
    List<Purchase> getAllOutstandingPurchases(@Param("user") User user);
    @Query("SELECT p FROM Purchase p WHERE p.id = :purchaseId AND p.user = :user")
    Purchase findByIdAndUser(@Param("purchaseId") Long purchaseId, @Param("user") User user);
}
