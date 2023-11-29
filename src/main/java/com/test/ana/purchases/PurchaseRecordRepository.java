package com.test.ana.purchases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    @Query( "SELECT pr FROM PurchaseRecord pr WHERE pr.purchase.id = :purchaseId AND pr.product.product_id = :productId")
    PurchaseRecord getPurchaseRecord(@Param("purchaseId") Long purchaseId, @Param("productId") Integer productId);
}
